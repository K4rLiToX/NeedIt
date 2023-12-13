package com.carlosdiestro.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.design_system.lists.HomeWishPlo
import com.carlosdiestro.design_system.lists.WishCategoryPlo
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.model.WishCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val homeService: HomeService
) : ViewModel() {

    private var _selectedWishId: MutableStateFlow<String> = MutableStateFlow("")

    val state: StateFlow<HomeDataState> =
        combine(
            _selectedWishId,
            homeService.currentUserWishes,
            homeService.isUserAnonymous
        ) { selectedWishId, wishlist, isAnonymous ->
            HomeDataState(
                wishes = wishlist.asPlo(),
                categories = homeService.extractCategories(wishlist),
                selectedWish = wishlist.find { it.id.toString() == selectedWishId },
                isAnonymous = isAnonymous
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeDataState()
            )

    fun onSelectedWish(id: String) {
        _selectedWishId.update {
            id
        }
    }

    fun shareWish() {
        viewModelScope.launch {
            state.value.selectedWish?.let {
                homeService.shareWish(it)
            }
        }
    }

    fun lockWish() {
        viewModelScope.launch {
            state.value.selectedWish?.let {
                homeService.lockWish(it)
            }
        }
    }

    fun deleteWish() {
        viewModelScope.launch {
            state.value.selectedWish?.let {
                homeService.deleteWish(it)
            }
        }
    }

    fun clearWishSelection() {
        onSelectedWish("")
    }
}

fun Wish.asPlo(): HomeWishPlo = HomeWishPlo(
    id = this.id.toString(),
    imageUrl = this.imageLocalPath.ifEmpty { this.imageUrl },
    shared = this.isShared,
    category = this.category.asPlo()
)

fun WishCategory.asPlo(): WishCategoryPlo = WishCategoryPlo.entries[this.ordinal]

fun List<Wish>.asPlo(): List<HomeWishPlo> = this.map { it.asPlo() }