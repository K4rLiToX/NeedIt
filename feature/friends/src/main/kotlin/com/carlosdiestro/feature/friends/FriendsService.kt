package com.carlosdiestro.feature.friends

import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendRepository
import com.carlosdiestro.user.domain.UserRepository
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.repository.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

typealias FriendWithWishes = Pair<Friend, List<Wish>>

class FriendsService @Inject constructor(
    private val friendRepository: FriendRepository,
    private val wishRepository: WishRepository,
    userRepository: UserRepository
) {

    val isAnonymous = userRepository.currentUser.map { it.isAnonymous }

    fun getFriendsWithWishes(): Flow<List<FriendWithWishes>> = flow {
        val friends = friendRepository.getAll().first()
        val ids = friends.map { it.id }
        val wishesGroupByFriend = wishRepository.getFriendsWishes(ids)
            .first()
            .groupByUserId()

        val friendWithWishes = friends.map { friend ->
            FriendWithWishes(
                first = friend,
                second = wishesGroupByFriend.getOrDefault(friend.id, emptyList())
            )
        }

        emit(friendWithWishes)
    }
}

private suspend fun List<Wish>.groupByUserId(): Map<String, List<Wish>> =
    withContext(Dispatchers.Default) {
        this@groupByUserId.groupBy { wish -> wish.userId }
    }