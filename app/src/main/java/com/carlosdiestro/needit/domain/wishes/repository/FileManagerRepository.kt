package com.carlosdiestro.needit.domain.wishes.repository

interface FileManagerRepository {
    suspend fun getImageUri(): String
}