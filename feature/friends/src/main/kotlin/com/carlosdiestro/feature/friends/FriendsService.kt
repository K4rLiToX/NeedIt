package com.carlosdiestro.feature.friends

import com.carlosdiestro.friend.domain.Friend
import com.carlosdiestro.friend.domain.FriendRepository
import com.carlosdiestro.user.domain.UserRepository
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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
        val friendWithWishes = wishRepository.getFriendsWishes(ids).map {
            it.groupBy { wish -> wish.userId }
        }.map {
            if (it.isEmpty()) {
                friends.map { f ->
                    FriendWithWishes(
                        f,
                        emptyList()
                    )
                }
            } else {
                it.keys.map { id ->
                    FriendWithWishes(
                        friends.find { f -> f.id == id }!!,
                        it[id]!!
                    )
                }
            }
        }
        emitAll(friendWithWishes)
    }
}