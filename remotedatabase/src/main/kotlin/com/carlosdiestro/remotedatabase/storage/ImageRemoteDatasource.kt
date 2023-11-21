package com.carlosdiestro.remotedatabase.storage

interface ImageRemoteDatasource {
    suspend fun create(bytes: ByteArray, userId: String): String
    fun delete(path: String)
}