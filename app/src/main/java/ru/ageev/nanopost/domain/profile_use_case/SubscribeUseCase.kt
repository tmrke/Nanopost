package ru.ageev.nanopost.domain.profile_use_case

import ru.ageev.nanopost.data.repository.ProfileRepository
import javax.inject.Inject

class SubscribeUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend fun execute(profileId: String) {
        profileRepository.subscribe(profileId)
    }
}