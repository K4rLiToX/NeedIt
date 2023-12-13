package com.carlosdiestro.feature.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.design_system.cards.NotificationPlo
import com.carlosdiestro.friend.domain.FriendRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val notificationsService: NotificationsService
) : ViewModel() {

    private lateinit var friendRequests: List<FriendRequest>

    val state: StateFlow<NotificationDataState> = notificationsService.getReceivedFriendRequests()
        .map {
            friendRequests = it
            NotificationDataState(
                notifications = it.asPlo()
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NotificationDataState()
        )

    fun acceptRequest(id: String) {
        viewModelScope.launch {
            val request = friendRequests.find { it.senderId == id }
            request?.let {
                notificationsService.accept(it)
            }
        }
    }

    fun rejectRequest(id: String) {
        viewModelScope.launch {
            val request = friendRequests.find { it.senderId == id }
            request?.let {
                notificationsService.reject(request)
            }
        }
    }
}

data class NotificationDataState(
    val notifications: List<NotificationPlo> = emptyList()
) {
    val empty: Boolean = notifications.isEmpty()
}

fun FriendRequest.asPlo(): NotificationPlo = NotificationPlo(
    senderId = senderId,
    imageUrl = senderProfilePictureUrl,
    username = senderUsername
)

fun List<FriendRequest>.asPlo(): List<NotificationPlo> = this.map { it.asPlo() }