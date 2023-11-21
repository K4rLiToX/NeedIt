package com.carlosdiestro.wish.data.repository

import com.carlosdiestro.wish.data.datasource.ImageLocalDatasource
import com.carlosdiestro.wish.data.datasource.ImageRemoteDatasource
import com.carlosdiestro.wish.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val localDatasource: ImageLocalDatasource,
    private val remoteDatasource: ImageRemoteDatasource
) : ImageRepository {
    override suspend fun getLocalPath(): String = localDatasource.getImageLocalPath()

    override suspend fun create(bytes: ByteArray, userId: String): String =
        remoteDatasource.create(bytes, userId)

    override fun delete(path: String) = remoteDatasource.delete(path)
}