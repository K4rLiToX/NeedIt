package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.repository.FileManagerRepository
import javax.inject.Inject

class GetImageUriUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(): String = fileManagerRepository.getImageUri()
}