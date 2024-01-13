package com.carlosdiestro.feature.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosdiestro.design_system.cards.FriendPlo
import com.carlosdiestro.design_system.cards.FriendWishPlo
import com.carlosdiestro.design_system.lists.FriendsWithWishesPlo
import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.wish.domain.model.Wish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class FriendsViewModel @Inject constructor(
    friendsService: FriendsService
) : ViewModel() {

    val state: StateFlow<FriendsDataState> = combine(
        friendsService.getFriendsWithWishes(),
        friendsService.isAnonymous
    ) { friendWithWishes, isAnonymous ->
        FriendsDataState(
            friendsWithWishes = friendWithWishes.asPlo(),
            isAnonymous = isAnonymous
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FriendsDataState()
        )

    fun onWishClick(id: String) {

    }

    fun onWishLongClick(id: String) {

    }
}

private fun FriendWithWishes.asPlo(): FriendsWithWishesPlo = FriendsWithWishesPlo(
    friend = this.first.asPlo(),
    wishes = this.second.asPlo()
)

private fun List<FriendWithWishes>.asPlo(): List<FriendsWithWishesPlo> = this.map { it.asPlo() }

private fun Friend.asPlo(): FriendPlo = FriendPlo(
    id = id,
    username = username,
    profilePictureUrl = profilePictureUrl
)

@JvmName("wishAsFriendWishPlo")
private fun Wish.asPlo(): FriendWishPlo = FriendWishPlo(
    id = id.toString(),
    imageUrl = imageUrl,
    title = title,
    price = price.toString(),
    isReserved = false
)

@JvmName("wishListAsFriendWishPloList")
private fun List<Wish>.asPlo(): List<FriendWishPlo> = this.map { it.asPlo() }

