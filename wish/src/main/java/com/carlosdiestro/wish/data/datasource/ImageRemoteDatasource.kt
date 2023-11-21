package com.carlosdiestro.wish.data.datasource

interface ImageRemoteDatasource {
    suspend fun create(bytes: ByteArray, userId: String): String
    fun delete(path: String)
}