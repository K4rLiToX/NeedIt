package com.carlosdiestro.design_system.lists

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carlosdiestro.design_system.cards.NiFriendRequestCard
import com.carlosdiestro.design_system.cards.NotificationPlo
import com.carlosdiestro.design_system.theme.dimensions

@Composable
fun NiFriendRequestsList(
    modifier: Modifier = Modifier,
    requests: List<NotificationPlo>,
    onAcceptRequest: (String) -> Unit,
    onRejectRequest: (String) -> Unit
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(vertical = MaterialTheme.dimensions.spacingM),
        modifier = modifier
    ) {
        items(
            items = requests,
            key = { request -> request.senderId }
        ) { notification ->
            NiFriendRequestCard(
                imageUrl = notification.imageUrl,
                message = notification.message,
                onAccept = { onAcceptRequest(notification.senderId) },
                onReject = { onRejectRequest(notification.senderId) },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}