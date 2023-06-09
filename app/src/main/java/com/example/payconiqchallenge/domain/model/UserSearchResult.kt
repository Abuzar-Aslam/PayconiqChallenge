package com.example.payconiqchallenge.domain.model

data class UserSearchResult(
    val totalCount: Int,
    val users: List<UserModel>
)