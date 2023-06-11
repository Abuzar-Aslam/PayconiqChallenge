package com.example.payconiqchallenge.data.model

import com.google.gson.annotations.SerializedName

/**
 * Represents a user repository response from the API.
 *
 * @property id The unique identifier of the repository.
 * @property name The name of the repository.
 * @property description The description of the repository (nullable).
 * @property watchCount The number of watchers of the repository.
 * @property starCount The number of stars the repository has received.
 */
data class UserRepositoryResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("watchers_count")
    val watchCount: Int,
    @SerializedName("stargazers_count")
    val starCount: Int
)