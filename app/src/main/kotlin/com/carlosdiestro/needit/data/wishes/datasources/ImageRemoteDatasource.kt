package com.carlosdiestro.needit.data.wishes.datasources

interface ImageRemoteDatasource {
    suspend fun create(imageLocalPath: String, userId: String): String
    fun delete(path: String)
}