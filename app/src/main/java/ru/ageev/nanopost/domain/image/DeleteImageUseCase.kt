package ru.ageev.nanopost.domain.image

import ru.ageev.nanopost.data.remote.repository.ImageRepository
import javax.inject.Inject

class DeleteImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    suspend fun execute(imageId: String) {
        imageRepository.deleteImage(imageId)
    }
}