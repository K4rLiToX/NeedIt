package com.carlosdiestro.needit.domain.wishes.repository

interface ImageRepository {
    suspend fun getLocalPath(): String
    suspend fun create(imageLocalPath: String, userId: String): String
    fun delete(path: String)
}