package ru.ageev.nanopost.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ageev.nanopost.data.model.Like

@Serializable
data class ApiPost(
    @SerialName("images") val images: List<ApiImage>,
    @SerialName("id") val id: String,
    @SerialName("text") val text: String? = null,
    @SerialName("likes") val likes: Like,
    @SerialName("owner") val owner: ApiProfileCompact,
    @SerialName("dateCreated") val dateCreated: Long,
)

