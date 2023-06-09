package com.example.payconiqchallenge.domain.model

data class UserRepositoryResult(
    val id: Int,
    val name: String,
    val full_name: String,
    val html_url: String,
    val description: String?,
)