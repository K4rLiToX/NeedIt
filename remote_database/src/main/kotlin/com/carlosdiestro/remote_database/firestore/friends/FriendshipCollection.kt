package com.carlosdiestro.remote_database.firestore.friends

import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject

class FriendshipCollection @Inject constructor(
    private val friendshipCollection: CollectionReference
) {

    fun create(friendship: FriendshipDto) {
        friendshipCollection
            .document(friendship.combinedId)
            .set(friendship)
    }
}