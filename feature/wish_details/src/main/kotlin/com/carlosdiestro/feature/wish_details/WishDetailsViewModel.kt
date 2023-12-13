package com.carlosdiestro.feature.wish_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.wish.domain.model.Book
import com.carlosdiestro.wish.domain.model.Clothes
import com.carlosdiestro.wish.domain.model.Footwear
import com.carlosdiestro.wish.domain.model.Other
import com.carlosdiestro.wish.domain.model.Wish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WishDetailsViewModel @Inject constructor(
    private val wishDetailService: WishDetailService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = WishDetailsNavArgs.fromSavedState(savedStateHandle)
    private val wishId = args.wishId

    private lateinit var wish: Wish

    val state: StateFlow<WishDetailsDataState> =
        combine(
            wishDetailService.getWish(wishId),
            wishDetailService.isUserAnonymous
        ) { wish, isAnonymous ->
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
                isAnonymous = isAnonymous
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
            wishDetailService.shareWish(wish)
        }
    }

    fun lockWish() {
        viewModelScope.launch {
            wishDetailService.lockWish(wish)
        }
    }
}