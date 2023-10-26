package com.carlosdiestro.needit.network.datasources

import com.carlosdiestro.needit.data.wishes.datasources.ImageRemoteDatasource
import com.carlosdiestro.needit.network.collections.ImagesCollection
import javax.inject.Inject

class ImageRemoteDatasourceImpl @Inject constructor(
    private val imagesCollection: ImagesCollection
) : ImageRemoteDatasource {

    override suspend fun insertImage(bytes: ByteArray, userId: String): String =
        imagesCollection.insertImage(bytes, userId)

    override suspend fun deleteImage(path: String) {
        imagesCollection.deleteImage(path)
    }
}