package com.carlosdiestro.design_system.lists

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carlosdiestro.design_system.cards.NiFollowableUserCard

data class FollowableUser(
    val id: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String,
    val status: FollowableUserStatus
)

enum class FollowableUserStatus {
    Followable,
    Friends,
    Pending
}

@Composable
fun NiFollowableUsersList(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    followableUsers: List<FollowableUser>,
    onSendRequestClick: (FollowableUser) -> Unit
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier
    ) {
        items(
            items = followableUsers,
            key = { user -> user.id }
        ) {
            NiFollowableUserCard(
                user = it,
                onClick = { onSendRequestClick(it) }
            )
        }
    }
}