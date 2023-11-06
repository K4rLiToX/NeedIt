package com.carlosdiestro.needit.domain.wishes.repository

interface ImageRepository {

    suspend fun insertImage(bytes: ByteArray, userId: String): String
    fun deleteImage(path: String)
}