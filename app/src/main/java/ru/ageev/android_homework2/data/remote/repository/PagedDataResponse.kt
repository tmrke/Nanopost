package ru.ageev.android_homework2.data.remote.repository

import kotlinx.serialization.Serializable

@Serializable
data class PagedDataResponse<T>(
    val count: Int,
    val total: Int,
    val offset: String? = null,
    val items: List<T>,
)