package com.carlosdiestro.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.design_system.lists.FollowableUser
import com.carlosdiestro.friend.GetFriendsUseCase
import com.carlosdiestro.user.usecases.GetSignedInUserUseCase
import com.carlosdiestro.user.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    getSignedInUser: GetSignedInUserUseCase,
    getUsers: GetUsersUseCase,
    getFriends: GetFriendsUseCase
) : ViewModel() {

    val state: StateFlow<SearchDataState> = combine(
        getSignedInUser(),
        getUsers(),
        getFriends()
    ) { signedInUser, users, friends ->
        val followableUsers = users.map { user ->
            FollowableUser(
                id = user.id,
                username = user.username,
                email = user.email,
                profilePictureUrl = user.profilePictureUrl,
                isFriendRequestSent = friends.map { it.id }.contains(user.id)
            )
        }
        SearchDataState(
            isSearchViewEnabled = !signedInUser.isAnonymous,
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

    }
}