package com.carlosdiestro.needit.features.wish_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.domain.wishes.usecases.GetWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.LockWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.ShareWishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WishDetailsViewModel @Inject constructor(
    getWish: GetWishUseCase,
    private val shareWish: ShareWishUseCase,
    private val lockWish: LockWishUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val wishId = savedStateHandle[argsWishId] ?: -1L

    val state: StateFlow<WishDetailsDataState> = getWish(wishId)
        .map { wish ->
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
                isbn = wish.isbn
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WishDetailsDataState()
        )

    fun uploadWish() {
        viewModelScope.launch {
            shareWish(wishId)
        }
    }

    fun privateWish() {
        viewModelScope.launch {
            lockWish(wishId)
        }
    }
}