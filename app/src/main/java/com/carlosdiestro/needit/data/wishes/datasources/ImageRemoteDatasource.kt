package com.carlosdiestro.needit.data.wishes.datasources

interface ImageRemoteDatasource {
    suspend fun insertImage(path: String, userId: String): String
}