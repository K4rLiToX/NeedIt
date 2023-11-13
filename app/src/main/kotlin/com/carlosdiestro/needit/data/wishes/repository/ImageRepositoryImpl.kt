package com.carlosdiestro.needit.data.wishes.repository

import com.carlosdiestro.needit.data.wishes.datasources.ImageLocalDatasource
import com.carlosdiestro.needit.data.wishes.datasources.ImageRemoteDatasource
import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import javax.inject.Inject

internal class ImageRepositoryImpl @Inject constructor(
    private val localDatasource: ImageLocalDatasource,
    private val remoteDatasource: ImageRemoteDatasource
) : ImageRepository {
    override suspend fun getLocalPath(): String = localDatasource.getImageLocalPath()

    override suspend fun create(imageLocalPath: String, userId: String): String =
        remoteDatasource.create(imageLocalPath, userId)

    override fun delete(path: String) = remoteDatasource.delete(path)
}