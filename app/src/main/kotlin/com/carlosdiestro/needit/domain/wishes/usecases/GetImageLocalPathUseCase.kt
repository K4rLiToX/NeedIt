package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.repository.FileManagerRepository
import javax.inject.Inject

class GetImageLocalPathUseCase @Inject constructor(
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(): String = fileManagerRepository.getImageLocalPath()
}