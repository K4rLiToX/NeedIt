package com.carlosdiestro.remote_database.firestore.friends

import com.carlosdiestro.friend.data.FriendshipRemoteDatasource
import com.carlosdiestro.friend.domain.Friendship
import javax.inject.Inject

class FriendshipRemoteDatasourceImpl @Inject constructor(
    private val collection: FriendshipCollection
) : FriendshipRemoteDatasource {

    override fun create(friendship: Friendship) = collection.create(friendship.asDto())
}

fun Friendship.asDto(): FriendshipDto = FriendshipDto(
    user1Id = user1Id,
    user1Username = user1Username,
    user1Email = user1Email,
    user1ProfilePictureUrl = user1ProfilePictureUrl,
    user2Id = user2Id,
    user2Username = user2Username,
    user2Email = user2Email,
    user2ProfilePictureUrl = user2ProfilePictureUrl
)