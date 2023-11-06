package com.carlosdiestro.needit.network.images

import com.carlosdiestro.needit.data.wishes.datasources.ImageRemoteDatasource
import javax.inject.Inject

class ImageRemoteDatasourceImpl @Inject constructor(
    private val imagesCollection: ImagesCollection
) : ImageRemoteDatasource {

    override suspend fun insertImage(bytes: ByteArray, userId: String): String =
        imagesCollection.insertImage(bytes, userId)

    override fun deleteImage(path: String) {
        imagesCollection.deleteImage(path)
    }
}