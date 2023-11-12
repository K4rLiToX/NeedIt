package com.carlosdiestro.needit.features.wish_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.usecases.GetWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.UpdateWishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
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

    private val wishId = savedStateHandle[argsWishId] ?: -1L

    private lateinit var wish: Wish

    val state: StateFlow<WishDetailsDataState> =
        combine(
            getWish(wishId),
            getSignedInUser()
        ) { wish, user ->
            this.wish = wish
            WishDetailsDataState(
                id = wish.id,
                imageUrl = wish.imageLocalPath.ifEmpty { wish.imageUrl },
                title = wish.title,
                subtitle = wish.subtitle,
                price = wish.price.toString(),
                description = wish.description,
                webUrl = wish.webUrl,
                isShared = wish.isShared,
                size = wish.size,
                color = wish.color,
                isbn = wish.isbn,
                isAnonymous = user.isAnonymous
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WishDetailsDataState()
        )

    fun shareWish() {
        viewModelScope.launch {
            updateWish(
                wish.copy(
                    isShared = true
                )
            )
        }
    }

    fun lockWish() {
        viewModelScope.launch {
            updateWish(
                wish.copy(
                    isShared = false
                )
            )
        }
    }
}