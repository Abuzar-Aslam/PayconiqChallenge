package com.example.payconiqchallenge.data.model

import com.google.gson.annotations.SerializedName

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