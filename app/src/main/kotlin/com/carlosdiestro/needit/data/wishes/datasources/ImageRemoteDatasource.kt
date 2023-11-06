package com.carlosdiestro.needit.data.wishes.datasources

interface ImageRemoteDatasource {
    suspend fun insertImage(bytes: ByteArray, userId: String): String
    fun deleteImage(path: String)
}