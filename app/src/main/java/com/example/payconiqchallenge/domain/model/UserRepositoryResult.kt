package com.example.payconiqchallenge.domain.model

/**
 * Data class representing a repository belonging to a user.
 *
 * @param id The ID of the repository.
 * @param name The name of the repository.
 * @param description The description of the repository, can be null.
 * @param starCount The number of stars the repository has received.
 * @param watchCount The number of users watching the repository.
 */
data class UserRepositoryResult(
    val id: Int,
    val name: String,
    val description: String?,
    val starCount: Int,
    val watchCount: Int
)