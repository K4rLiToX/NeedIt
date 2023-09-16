package com.carlosdiestro.needit.data.wishes.repository

import com.carlosdiestro.needit.core.di.ApplicationScope
import com.carlosdiestro.needit.data.wishes.datasources.ImageRemoteDatasource
import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val remoteDatasource: ImageRemoteDatasource,
    @ApplicationScope private val appDispatcher: CoroutineScope
) : ImageRepository {

    override suspend fun insertImage(path: String, userId: String): String =
        withContext(appDispatcher.coroutineContext) {
            remoteDatasource.insertImage(path, userId)
        }
}