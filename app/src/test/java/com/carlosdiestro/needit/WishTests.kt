package com.carlosdiestro.needit

import com.carlosdiestro.needit.domain.wishes.GetMyWishesUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WishTests {
    lateinit var repository: FakeWishRepository
    lateinit var getMyWishes: GetMyWishesUseCase

    @Before
    fun setup() {
        repository = FakeWishRepository()
        getMyWishes = GetMyWishesUseCase(repository)
    }

    @Test
    fun `Getting my wishes`() = runBlocking {
        val myWishes = repository.wishes.first()
        assertTrue(myWishes.isNotEmpty())
    }
}