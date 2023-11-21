package com.carlosdiestro.remote_database.storage

import com.carlosdiestro.wish.data.datasource.ImageRemoteDatasource
import javax.inject.Inject

internal class ImageRemoteDatasourceImpl @Inject constructor(
    private val imagesCollection: ImagesCollection
) : ImageRemoteDatasource {

    override suspend fun create(bytes: ByteArray, userId: String): String =
        imagesCollection.create(bytes, userId)

    override fun delete(path: String) {
        imagesCollection.delete(path)
    }
}