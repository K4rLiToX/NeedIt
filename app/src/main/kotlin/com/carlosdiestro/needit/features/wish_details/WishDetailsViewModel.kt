package com.carlosdiestro.needit.features.wish_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import com.carlosdiestro.needit.domain.wishes.Book
import com.carlosdiestro.needit.domain.wishes.Clothes
import com.carlosdiestro.needit.domain.wishes.Footwear
import com.carlosdiestro.needit.domain.wishes.Other
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.usecases.GetWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.UpdateWishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WishDetailsViewModel @Inject constructor(
    getWish: GetWishUseCase,
    getSignedInUser: GetSignedInUserUseCase,
    private val updateWish: UpdateWishUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val wishId = savedStateHandle[argsWishId] ?: ""

    private lateinit var wish: Wish

    val state: StateFlow<WishDetailsDataState> =
        combine(
            getWish(wishId),
            getSignedInUser()
        ) { wish, user ->
            this.wish = wish
            val state = WishDetailsDataState(
                id = wish.id.toString(),
                imageUrl = wish.imageLocalPath.ifEmpty { wish.imageUrl },
                title = wish.title,
                subtitle = wish.subtitle,
                price = wish.price.toString(),
                description = wish.description,
                webUrl = wish.webUrl,
                isShared = wish.isShared,
                isAnonymous = user.isAnonymous
            )
            when (wish) {
                is Clothes -> state.copy(size = wish.size, color = wish.color)
                is Footwear -> state.copy(size = wish.size, color = wish.color)
                is Book -> state.copy(isbn = wish.isbn)
                is Other -> state
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WishDetailsDataState()
        )

    fun shareWish() {
        viewModelScope.launch {
            wish.let {
                val updatedWish = when (it) {
                    is Clothes -> it.copy(isShared = true)
                    is Footwear -> it.copy(isShared = true)
                    is Book -> it.copy(isShared = true)
                    is Other -> it.copy(isShared = true)
                }
                updateWish(updatedWish)
            }
        }
    }

    fun lockWish() {
        viewModelScope.launch {
            wish.let {
                val updatedWish = when (it) {
                    is Clothes -> it.copy(isShared = false)
                    is Footwear -> it.copy(isShared = false)
                    is Book -> it.copy(isShared = false)
                    is Other -> it.copy(isShared = false)
                }
                updateWish(updatedWish)
            }
        }
    }
}