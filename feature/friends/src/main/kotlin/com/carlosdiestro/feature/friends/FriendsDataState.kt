package com.carlosdiestro.feature.friends

internal data class FriendsDataState(
    val friends: List<String> = emptyList()
) {
    val showEmptyScreen: Boolean = friends.isEmpty()
}