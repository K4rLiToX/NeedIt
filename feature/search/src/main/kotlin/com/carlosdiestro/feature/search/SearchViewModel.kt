package com.carlosdiestro.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.design_system.lists.FollowableUser
import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendStatus
import com.carlosdiestro.friend.usecases.GetFriendsUseCase
import com.carlosdiestro.friend.usecases.SendFriendRequestUseCase
import com.carlosdiestro.user.domain.User
import com.carlosdiestro.user.usecases.GetSignedInUserUseCase
import com.carlosdiestro.user.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    getSignedInUser: GetSignedInUserUseCase,
    getUsers: GetUsersUseCase,
    getFriends: GetFriendsUseCase,
    private val sendFriendRequest: SendFriendRequestUseCase
) : ViewModel() {

    val state: StateFlow<SearchDataState> = combine(
        getSignedInUser(),
        getUsers(),
        getFriends()
    ) { localUser, users, friends ->
        signedInUser = localUser
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
            isSearchViewEnabled = !localUser.isAnonymous,
            users = followableUsers
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchDataState()
    )

    private lateinit var signedInUser: User

    var query by mutableStateOf("")
        private set

    fun onQueryChange(value: String) {
        query = value
    }

    fun onSendRequestClick(followableUser: FollowableUser) {
        viewModelScope.launch {
            sendFriendRequest(
                friend = followableUser.asFriend(),
                userId = signedInUser.id,
                username = signedInUser.username,
                email = signedInUser.email,
                profilePictureUrl = signedInUser.profilePictureUrl
            )
        }
    }
}

internal fun FollowableUser.asFriend(): Friend = Friend(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl,
    friendStatus = FriendStatus.Pending
)