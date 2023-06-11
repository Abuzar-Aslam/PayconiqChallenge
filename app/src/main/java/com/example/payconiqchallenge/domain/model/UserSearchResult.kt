package com.example.payconiqchallenge.domain.model

/**
 * Data class representing the result of a user search operation.
 *
 * @param totalCount The total count of users matching the search query.
 * @param users The list of user models representing the search results.
 */
data class UserSearchResult(
    val totalCount: Int,
    val users: List<UserModel>
)