package com.carlosdiestro.needit

import com.carlosdiestro.needit.core.design_system.components.navigation.WishCategory
import com.carlosdiestro.needit.domain.wishes.usecases.GetMyWishesUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.GetWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.RemoveWishUseCase
import com.carlosdiestro.needit.domain.wishes.usecases.InsertWishUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WishTests {
    private lateinit var repository: FakeWishRepository
    private lateinit var getMyWishes: GetMyWishesUseCase
    private lateinit var getWish: GetWishUseCase
    private lateinit var upsertWish: InsertWishUseCase
    private lateinit var removeWish: RemoveWishUseCase

    @Before
    fun setup() {
        repository = FakeWishRepository()
        getMyWishes = GetMyWishesUseCase(repository)
        getWish = GetWishUseCase(repository)
        upsertWish = InsertWishUseCase(repository)
        removeWish = RemoveWishUseCase(repository)
    }

    @Test
    fun `Getting my wishes`() = runBlocking {
        val myWishes = repository.wishes.first()
        assertTrue(myWishes.isNotEmpty())
    }

    @Test
    fun `Insert & Get wish`() = runBlocking {
        upsertWish(
            id = -1,
            imageUrl = "",
            title = "Mouse",
            subtitle = "Logitech",
            price = "100.0",
            webUrl = "",
            description = "",
            category = WishCategory.Tech,
            size = "",
            color = "",
            isbn = ""
        )
        val insertedWish = getWish(-1)
        assertTrue(insertedWish.title == "Mouse")
        assertTrue(WishCategory.Tech == insertedWish.category)
    }

    @Test
    fun `Remove wish`(): Unit = runBlocking {
        removeWish(0)
        assertThrows(NullPointerException::class.java) {
            runBlocking {
                getWish(0)
            }
        }
    }

    @Test
    fun `Update wish`() = runBlocking {
        val wish = getWish(0)
        upsertWish(
            id = wish.id,
            imageUrl = wish.imageUrl,
            title = "Change Title",
            subtitle = wish.subtitle,
            price = wish.price.toString(),
            webUrl = wish.webUrl,
            description = wish.description,
            category = wish.category,
            size = "",
            color = "",
            isbn = ""
        )
        val updatedWish = getWish(0)
        assertTrue(updatedWish.title == "Change Title")
    }
}