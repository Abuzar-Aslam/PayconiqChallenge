package com.example.payconiqchallenge.data.model

import com.google.gson.annotations.SerializedName

/**
 * Represents a user item response from the API.
 *
 * @property login The username of the user.
 * @property id The unique identifier of the user.
 * @property avatarUrl The URL of the user's avatar image.
 * @property htmlUrl The URL of the user's profile page.
 */
data class UserItemResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String
)