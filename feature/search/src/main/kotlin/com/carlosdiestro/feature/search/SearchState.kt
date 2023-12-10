package com.carlosdiestro.feature.search

import com.carlosdiestro.design_system.lists.FollowableUser

internal data class SearchDataState(
    val isSearchViewEnabled: Boolean = false,
    val users: List<FollowableUser> = emptyList()
)

