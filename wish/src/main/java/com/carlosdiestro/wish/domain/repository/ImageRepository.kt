package com.carlosdiestro.wish.domain.repository

interface ImageRepository {
    suspend fun getLocalPath(): String
    suspend fun create(bytes: ByteArray, userId: String): String
    fun delete(path: String)
}