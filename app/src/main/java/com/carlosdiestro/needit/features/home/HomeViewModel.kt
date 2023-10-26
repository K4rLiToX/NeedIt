package com.carlosdiestro.needit.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import com.carlosdiestro.needit.core.mappers.asPlo
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.usecases.GetMyWishesUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.LockWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.RemoveWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.ShareWishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMyWishes: GetMyWishesUseCase,
    private val removeWish: RemoveWishUseCase,
    private val shareWish: ShareWishUseCase,
    private val lockWish: LockWishUseCase
) : ViewModel() {


    private var _selectedWishId: MutableStateFlow<Long> = MutableStateFlow(-1)
    private val selectedWishId = _selectedWishId.asStateFlow()

    val state: StateFlow<HomeDataState> =
        combine(
            selectedWishId,
            getMyWishes()
        ) { selectedWishId, wishlist ->
            HomeDataState(
                wishes = wishlist.asPlo(),
                categories = getCategories(wishlist),
                selectedWish = wishlist.find { it.id == selectedWishId }
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeDataState()
            )

    private fun getCategories(wishes: List<Wish>): List<WishCategoryPlo> =
        wishes
            .map { it.category.asPlo() }
            .toSet()
            .sortedBy { it.ordinal }

    fun onSelectedWish(id: Long) {
        _selectedWishId.update {
            id
        }
    }

    fun uploadWish() {
        viewModelScope.launch {
            val wish = state.value.selectedWish
            wish?.let {
                shareWish(it.id)
                onSelectedWish(-1)
            }
        }
    }

    fun privateWish() {
        viewModelScope.launch {
            val wish = state.value.selectedWish
            wish?.let {
                lockWish(it.id)
                onSelectedWish(-1)
            }
        }
    }

    fun deleteWish() {
        viewModelScope.launch {
            val wish = state.value.selectedWish
            wish?.let {
                removeWish(
                    id = it.id,
                    cloudId = it.cloudId,
                    imageUrl = it.imageUrl,
                    imageLocalPath = it.imageLocalPath
                )
            }
        }
    }

    fun clearWishSelection() {
        onSelectedWish(-1)
    }
}