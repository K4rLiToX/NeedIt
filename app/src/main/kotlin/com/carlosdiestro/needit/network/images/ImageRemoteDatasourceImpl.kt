package com.carlosdiestro.needit.network.images

import com.carlosdiestro.needit.data.wishes.datasources.ImageRemoteDatasource
import javax.inject.Inject

internal class ImageRemoteDatasourceImpl @Inject constructor(
    private val imagesCollection: ImagesCollection
) : ImageRemoteDatasource {

    override suspend fun create(imageLocalPath: String, userId: String): String =
        imagesCollection.create(imageLocalPath, userId)

    override fun delete(path: String) {
        imagesCollection.delete(path)
    }
}