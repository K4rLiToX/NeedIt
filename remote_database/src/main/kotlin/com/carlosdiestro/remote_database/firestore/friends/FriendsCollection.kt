package com.carlosdiestro.remote_database.firestore.friends

import com.carlosdiestro.remote_database.CollectionsPath
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject

class FriendsCollection @Inject constructor(
    private val usersCollection: CollectionReference
) {

    fun create(userId: String, friend: FriendDto) {
        usersCollection
            .document(userId)
            .collection(CollectionsPath.userFriends)
            .document(friend.id)
            .set(friend)
    }
}