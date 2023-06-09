package com.example.payconiqchallenge.data.model

data class UserDetailResponse(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val name: String?,
)