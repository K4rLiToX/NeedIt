package com.carlosdiestro.needit.data.wishes.datasources

interface ImageRemoteDatasource {
    suspend fun create(bytes: ByteArray, userId: String): String
    fun delete(path: String)
}