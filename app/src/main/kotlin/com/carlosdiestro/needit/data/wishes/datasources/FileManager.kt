package com.carlosdiestro.needit.data.wishes.datasources

interface FileManager {
    suspend fun getImageUri(): String
}