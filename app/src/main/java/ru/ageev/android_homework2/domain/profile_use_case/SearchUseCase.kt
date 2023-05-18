package ru.ageev.android_homework2.domain.profile_use_case

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.ageev.android_homework2.data.model.Profile
import ru.ageev.android_homework2.data.remote.repository.ProfileRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend fun execute(query: String): Flow<PagingData<Profile>> {
        return profileRepository.searchProfile(query)
    }
}