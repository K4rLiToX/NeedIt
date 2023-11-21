package com.carlosdiestro.wish.usecases

import com.carlosdiestro.wish.domain.repository.ImageRepository
import javax.inject.Inject

class GetImageLocalPathUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(): String = repository.getLocalPath()
}