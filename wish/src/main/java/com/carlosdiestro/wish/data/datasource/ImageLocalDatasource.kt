package com.carlosdiestro.wish.data.datasource

interface ImageLocalDatasource {
    suspend fun getImageLocalPath(): String
}