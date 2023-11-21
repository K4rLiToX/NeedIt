package com.carlosdiestro.device_storage

import com.carlosdiestro.wish.data.datasource.ImageLocalDatasource
import javax.inject.Inject

class ImageLocalDatasourceImpl @Inject constructor(
    private val dao: ImageStorageDao
) : ImageLocalDatasource {

    override suspend fun getImageLocalPath(): String = dao.getImagePath()
}