package com.carlosdiestro.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.design_system.lists.HomeWishPlo
import com.carlosdiestro.design_system.lists.WishCategoryPlo
import com.carlosdiestro.user.usecases.GetSignedInUserUseCase
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.model.WishCategory
import com.carlosdiestro.wish.usecases.DeleteWishUseCase
import com.carlosdiestro.wish.usecases.GetMyWishesUseCase
import com.carlosdiestro.wish.usecases.LockWishUseCase
import com.carlosdiestro.wish.usecases.ShareWishUseCase
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
    getMyWishes: GetMyWishesUseCase,
    getSignedInUser: GetSignedInUserUseCase,
    private val deleteWish: DeleteWishUseCase,
    private val shareWish: ShareWishUseCase,
    private val lockWish: LockWishUseCase
) : ViewModel() {

    private var _selectedWishId: MutableStateFlow<String> = MutableStateFlow("")

    val state: StateFlow<HomeDataState> =
        combine(
            _selectedWishId,
            getMyWishes(),
            getSignedInUser()
        ) { selectedWishId, wishlist, user ->
            HomeDataState(
                wishes = wishlist.asPlo(),
                categories = getCategories(wishlist),
                selectedWish = wishlist.find { it.id.toString() == selectedWishId },
                isAnonymous = user.isAnonymous
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeDataState()
            )

    private fun getCategories(wishes: List<Wish>): List<WishCategoryPlo> =
        wishes
            .map<Wish, WishCategoryPlo> { it.category.asPlo() }
            .toSet()
            .sortedBy { it.ordinal }

    fun onSelectedWish(id: String) {
        _selectedWishId.update {
            id
        }
    }

    fun shareWish() {
        viewModelScope.launch {
            state.value.selectedWish?.let {
                shareWish(it)
            }
        }
    }

    fun lockWish() {
        viewModelScope.launch {
            state.value.selectedWish?.let {
                lockWish(it)
            }
        }
    }

    fun deleteWish() {
        viewModelScope.launch {
            state.value.selectedWish?.let {
                deleteWish(it)
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