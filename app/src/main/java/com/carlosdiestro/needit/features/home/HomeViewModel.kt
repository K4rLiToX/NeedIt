package com.carlosdiestro.needit.features.home

import androidx.lifecycle.ViewModel
import com.carlosdiestro.needit.core.design_system.components.cards.SimpleWishPLO
import com.carlosdiestro.needit.core.design_system.components.menu.SortType
import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.core.extensions.launchCollect
import com.carlosdiestro.needit.core.mappers.toPLO
import com.carlosdiestro.needit.domain.wishes.GetMyWishesUseCase
import com.carlosdiestro.needit.domain.wishes.SortWishesUseCase
import com.carlosdiestro.needit.domain.wishes.Wish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMyWishes: GetMyWishesUseCase,
    private val sortWishes: SortWishesUseCase
) : ViewModel() {
    
    private var _state: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()
    private lateinit var wishes: List<Wish>

    init {
        fetchMyWishes()
    }

    fun updateSortType(sortType: SortType) {
        _state.update { currentState ->
            currentState.copy(
                wishes = sortWishes(sortType, wishes).toPLO(),
                sortOptionSelected = sortType
            )
        }
    }

    private fun fetchMyWishes() {
        launchCollect(getMyWishes()) { list ->
            wishes = list
            _state.update { currentState ->
                currentState.copy(
                    wishes = sortWishes(currentState.sortOptionSelected, list).toPLO(),
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
                    .sortedBy { it }
            )
    }
}

data class HomeUiState(
    val wishes: List<SimpleWishPLO> = emptyList(),
    val tabs: List<WishCategory> = emptyList(),
    val sortOptionSelected: SortType = SortType.HighToLow,
    val isEmpty: Boolean = false
)