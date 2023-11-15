package com.carlosdiestro.needit.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.core.design_system.components.lists.WishCategoryPlo
import com.carlosdiestro.needit.core.mappers.asPlo
import com.carlosdiestro.needit.domain.users.usecases.GetSignedInUserUseCase
import com.carlosdiestro.needit.domain.wishes.Book
import com.carlosdiestro.needit.domain.wishes.Clothes
import com.carlosdiestro.needit.domain.wishes.Footwear
import com.carlosdiestro.needit.domain.wishes.Other
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.usecases.DeleteWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.GetMyWishesUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.UpdateWishUseCase
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
    private val updateWish: UpdateWishUseCase,
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
            .map { it.category.asPlo() }
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
            state.value.selectedWish?.let {
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