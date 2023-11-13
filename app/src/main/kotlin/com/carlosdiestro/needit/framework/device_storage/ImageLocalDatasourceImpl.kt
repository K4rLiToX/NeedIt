package com.carlosdiestro.needit.framework.device_storage

import com.carlosdiestro.needit.data.wishes.datasources.ImageLocalDatasource
import javax.inject.Inject

internal class ImageLocalDatasourceImpl @Inject constructor(
    private val dao: ImageStorageDao
) : ImageLocalDatasource {

    override suspend fun getImageLocalPath(): String = dao.getImagePath()
}