package com.carlosdiestro.remote_database.firestore.friends

import com.carlosdiestro.friend.data.FriendRemoteDatasource
import com.carlosdiestro.friend.domain.Friend
import javax.inject.Inject

class FriendRemoteDatasourceImpl @Inject constructor(
    private val friendsCollection: FriendsCollection
) : FriendRemoteDatasource {

    override fun create(userId: String, friend: Friend) =
        friendsCollection.create(userId, friend.asDto())
}

fun Friend.asDto(): FriendDto = FriendDto(
    id = id,
    username = username,
    email = email,
    profilePictureUrl = profilePictureUrl
)