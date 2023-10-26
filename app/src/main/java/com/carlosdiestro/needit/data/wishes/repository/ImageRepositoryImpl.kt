package com.carlosdiestro.needit.data.wishes.repository

import com.carlosdiestro.needit.core.di.ApplicationScope
import com.carlosdiestro.needit.core.di.IoDispatcher
import com.carlosdiestro.needit.data.wishes.datasources.ImageRemoteDatasource
import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val remoteDatasource: ImageRemoteDatasource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationScope private val appDispatcher: CoroutineScope
) : ImageRepository {

    override suspend fun insertImage(bytes: ByteArray, userId: String): String =
        withContext(appDispatcher.coroutineContext) {
            remoteDatasource.insertImage(bytes, userId)
        }

    override suspend fun deleteImage(path: String) = withContext(ioDispatcher) {
        remoteDatasource.deleteImage(path)
    }
}