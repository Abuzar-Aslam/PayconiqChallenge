package com.example.payconiqchallenge.domain.model

/**
 * Data class representing the result of a user detail operation.
 *
 * @param login The username of the user.
 * @param id The ID of the user.
 * @param avatarUrl The URL of the user's avatar image.
 * @param name The name of the user.
 * @param followers The number of followers of the user. Can be null.
 * @param following The number of users the user is following. Can be null.
 */
data class UserDetailResult(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val name: String,
    val followers: String?,
    val following: String?,
)