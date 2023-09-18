package com.carlosdiestro.needit.domain.wishes.repository

interface ImageRepository {

    suspend fun insertImage(path: String, userId: String): String
    suspend fun deleteImage(path: String)
}