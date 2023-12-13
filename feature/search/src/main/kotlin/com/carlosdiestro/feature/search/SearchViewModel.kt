package com.carlosdiestro.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.design_system.lists.FollowableUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val searchService: SearchService
) : ViewModel() {

    val state: StateFlow<SearchDataState> = combine(
        searchService.getFollowableUsers(),
        searchService.isUserAnonymous
    ) { followableUsers, isAnonymous ->
        SearchDataState(
            isSearchViewEnabled = !isAnonymous,
            users = followableUsers
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchDataState()
    )

    var query by mutableStateOf("")
        private set

    fun onQueryChange(value: String) {
        query = value
    }

    fun onSendRequestClick(followableUser: FollowableUser) {
        viewModelScope.launch {
            searchService.sendFriendRequest(followableUser)
        }
    }
}
