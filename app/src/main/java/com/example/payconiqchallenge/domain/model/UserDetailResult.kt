package com.example.payconiqchallenge.domain.model

data class UserDetailResult(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val name: String?,
    val followers: String?,
    val following: String?,
)