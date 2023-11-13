package com.carlosdiestro.needit.domain.wishes.usecases

import com.carlosdiestro.needit.domain.wishes.repository.ImageRepository
import javax.inject.Inject

class GetImageLocalPathUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(): String = repository.getLocalPath()
}