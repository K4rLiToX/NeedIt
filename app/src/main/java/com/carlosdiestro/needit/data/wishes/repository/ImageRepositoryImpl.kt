package com.carlosdiestro.needit.data.wishes.repository

import com.carlosdiestro.needit.data.wishes.datasources.ImageRemoteDatasource
import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val remoteDatasource: ImageRemoteDatasource
) : ImageRepository {

    override suspend fun insertImage(bytes: ByteArray, userId: String): String =
        remoteDatasource.insertImage(bytes, userId)

    override suspend fun deleteImage(path: String) = remoteDatasource.deleteImage(path)
}