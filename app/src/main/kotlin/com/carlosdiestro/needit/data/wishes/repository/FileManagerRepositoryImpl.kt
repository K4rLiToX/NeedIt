package com.carlosdiestro.needit.data.wishes.repository

import com.carlosdiestro.needit.data.wishes.datasources.FileManager
import com.carlosdiestro.needit.domain.wishes.repository.FileManagerRepository
import javax.inject.Inject

internal class FileManagerRepositoryImpl @Inject constructor(
    private val fileManager: FileManager
) : FileManagerRepository {
    override suspend fun getImageLocalPath(): String = fileManager.getImageLocalPath()
}