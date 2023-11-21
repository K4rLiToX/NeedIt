package com.carlosdiestro.needit

import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.wish.domain.model.Book
import com.carlosdiestro.wish.domain.model.Clothes
import com.carlosdiestro.wish.domain.model.Wish
import com.carlosdiestro.wish.domain.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeWishRepository : WishRepository {
    override val wishes: Flow<List<Wish>> = flowOf(list)
    override suspend fun getWish(id: Long): Wish = list.find { it.id == id }!!

    override suspend fun upsertWish(wish: Wish) {
        if (wish.id != -1L) {
            list.removeIf { it.id == wish.id }
            list.add(wish)
        } else {
            list.add(wish)
        }
    }

    override suspend fun removeWish(id: Long) {
        list.removeIf { it.id == id }
    }

    override suspend fun share(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun lock(id: Long) {
        TODO("Not yet implemented")
    }
}

val list = mutableListOf(
    Clothes(
        id = 0,
        imageUrl = "https://img01.ztat" +
                ".net/article/spp-media-p1/7390c3ace4433d2e96bc0dcdd4b6ebc6/44459950a6fe489d99a6e00e3b85c62d.jpg?imwidth=1800",
        price = 55.0,
        description = "",
        webUrl = "",
        isShared = false,
        category = WishCategory.Clothes,
        title = "T-Shirt",
        subtitle = "Adidas",
        size = "L",
        color = "White"
    ),
    Book(
        id = 1,
        imageUrl = "https://images.thalia" +
                ".media/03/-/66ae076aecbf4bfb9d801fd776a17bb5/the-way-of-kings-02-taschenbuch-brandon-sanderson-englisch.jpeg",
        price = 35.0,
        description = "",
        webUrl = "",
        isShared = false,
        category = WishCategory.Books,
        title = "The Way of the Kings",
        subtitle = "Brandon Sanderson",
        isbn = "876545678"
    )
)