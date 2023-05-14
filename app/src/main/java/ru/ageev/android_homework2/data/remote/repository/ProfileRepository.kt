package ru.ageev.android_homework2.data.remote.repository


import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.ageev.android_homework2.data.model.Post
import ru.ageev.android_homework2.data.model.Profile


interface ProfileRepository {
    suspend fun getProfile(profilerId: String): Profile

    suspend fun getProfilePosts(profilerId: String): Flow<PagingData<Post>>

    suspend fun subscribe(profilerId: String)
}