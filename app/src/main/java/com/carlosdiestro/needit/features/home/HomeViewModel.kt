package com.carlosdiestro.needit.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.needit.core.design_system.components.cards.SimpleWishPLO
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.core.extensions.launchCollect
import com.carlosdiestro.needit.core.mappers.toPLO
import com.carlosdiestro.needit.domain.wishes.Wish
import com.carlosdiestro.needit.domain.wishes.usecases.GetMyWishesUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.LockWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.RemoveWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.ShareWishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private var _state: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()
    private lateinit var wishes: List<Wish>
    var selectedWishId: Long? = null
        private set

    init {
        fetchMyWishes()
    }

    private fun fetchMyWishes() {
        launchCollect(getMyWishes()) { list ->
            wishes = list
            _state.update { currentState ->
                currentState.copy(
                    wishes = list.toPLO(),
                    tabs = getCategoryTabs(list.toPLO()),
                    isEmpty = list.isEmpty()
                )
            }
        }
    }

    private fun getCategoryTabs(wishes: List<SimpleWishPLO>): List<WishCategory> {
        return listOf(WishCategory.All)
            .plus(
                wishes
                    .map { it.category }
                    .toSet()
                    .sortedBy { it.ordinal }
            )
    }

    fun onSelectedWish(id: Long) {
        selectedWishId = id
        _state.update {
            it.copy(
                wishIsShared = wishes.find { wish -> wish.id == id }!!.isShared
            )
        }
    }

    fun uploadWish() {
        viewModelScope.launch {
            selectedWishId?.let {
                shareWish(it)
            }
            selectedWishId = null
        }
    }

    fun privateWish() {
        viewModelScope.launch {
            selectedWishId?.let {
                lockWish(it)
            }
            selectedWishId = null
        }
    }

    fun deleteWish() {
        viewModelScope.launch {
            selectedWishId?.let {
                removeWish(it)
            }
        }
    }

}

data class HomeUiState(
    val wishes: List<SimpleWishPLO> = emptyList(),
    val tabs: List<WishCategory> = emptyList(),
    val isEmpty: Boolean = false,
    val wishIsShared: Boolean = false
)