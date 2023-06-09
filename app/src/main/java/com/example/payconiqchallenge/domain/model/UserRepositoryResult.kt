package com.example.payconiqchallenge.domain.model

data class UserRepositoryResult(
    val id: Int,
    val name: String,
    val description: String?,
    val starCount: Int,
    val watchCount: Int
)