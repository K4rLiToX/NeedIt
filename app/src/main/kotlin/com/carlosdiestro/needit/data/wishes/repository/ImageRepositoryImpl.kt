package com.carlosdiestro.needit.data.wishes.repository

import com.carlosdiestro.needit.data.wishes.datasources.ImageRemoteDatasource
import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import javax.inject.Inject

internal class ImageRepositoryImpl @Inject constructor(
    private val remoteDatasource: ImageRemoteDatasource
) : ImageRepository {

    override suspend fun create(bytes: ByteArray, userId: String): String =
        remoteDatasource.create(bytes, userId)

    override fun delete(path: String) = remoteDatasource.delete(path)
}