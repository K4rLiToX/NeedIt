package com.carlosdiestro.needit.data.wishes.datasources

interface FileManager {
    suspend fun getImageLocalPath(): String
}