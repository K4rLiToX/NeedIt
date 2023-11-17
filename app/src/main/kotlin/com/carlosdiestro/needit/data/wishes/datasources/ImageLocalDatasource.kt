package com.carlosdiestro.needit.data.wishes.datasources

interface ImageLocalDatasource {
    suspend fun getImageLocalPath(): String
}