package ru.ageev.nanopost.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiProfile(
    @SerialName("id") val id: String,
    @SerialName("username") val username: String,
    @SerialName("displayName") val displayName: String? = null,
    @SerialName("bio") val bio: String? = null,
    @SerialName("avatarId") val avatarId: String? = null,
    @SerialName("avatarSmall") val avatarSmall: String? = null,
    @SerialName("avatarLarge") val avatarLarge: String? = null,
    @SerialName("subscribed") val subscribed: Boolean = false,
    @SerialName("subscribersCount") val subscribersCount: Int,
    @SerialName("postsCount") val postsCount: Int,
    @SerialName("imagesCount") val imagesCount: Int,
    @SerialName("images") val images: List<ApiImage>,
)
