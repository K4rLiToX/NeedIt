package com.carlosdiestro.feature.friends

import com.carlosdiestro.design_system.lists.FriendsWithWishesPlo

internal data class FriendsDataState(
    val friendsWithWishes: List<FriendsWithWishesPlo> = emptyList()
) {
    val showEmptyScreen: Boolean = friendsWithWishes.isEmpty()
}