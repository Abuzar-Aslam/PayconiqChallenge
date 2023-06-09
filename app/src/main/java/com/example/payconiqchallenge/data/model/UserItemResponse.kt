package com.example.payconiqchallenge.data.model

import com.google.gson.annotations.SerializedName

data class UserItemResponse(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String
)