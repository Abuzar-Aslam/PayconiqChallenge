package com.example.payconiqchallenge.data.model

import com.google.gson.annotations.SerializedName

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