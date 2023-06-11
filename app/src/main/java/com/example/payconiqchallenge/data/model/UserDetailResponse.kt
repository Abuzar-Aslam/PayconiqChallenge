package com.example.payconiqchallenge.data.model

import com.google.gson.annotations.SerializedName

/**
 * Represents a user detail response from the API.
 *
 * @property login The username of the user.
 * @property id The unique identifier of the user.
 * @property avatarUrl The URL of the user's avatar image.
 * @property name The name of the user.
 * @property followers The number of followers of the user (nullable).
 * @property following The number of users the user is following (nullable).
 */
data class UserDetailResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("followers")
    val followers: String?,
    @SerializedName("following")
    val following: String?,
)