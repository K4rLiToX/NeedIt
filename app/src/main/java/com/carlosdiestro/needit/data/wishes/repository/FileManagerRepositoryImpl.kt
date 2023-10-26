package com.carlosdiestro.needit.data.wishes.repository

import com.carlosdiestro.needit.data.wishes.datasources.FileManager
import com.carlosdiestro.needit.domain.wishes.repository.FileManagerRepository
import javax.inject.Inject

class FileManagerRepositoryImpl @Inject constructor(
    private val fileManager: FileManager
) : FileManagerRepository {
    override suspend fun getImageUri(): String = fileManager.getImageUri()

    override suspend fun delete(uri: String) = fileManager.delete(uri)
}