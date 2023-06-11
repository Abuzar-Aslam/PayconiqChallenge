package com.example.payconiqchallenge.domain.model

/**
 * Data class representing a user.
 *
 * @param login The username of the user.
 * @param id The ID of the user.
 * @param avatarUrl The URL of the user's avatar image.
 * @param htmlUrl The URL of the user's profile page.
 */
data class UserModel(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val htmlUrl: String
)