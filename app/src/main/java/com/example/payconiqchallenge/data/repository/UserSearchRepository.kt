package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.domain.model.UserSearchResult

/**
 * Repository interface for searching users.
 */
interface UserSearchRepository {

    /**
     * Searches for users based on the provided query and page number.
     *
     * @param query The search query.
     * @param page The page number of the search results.
     * @return A [Result] object representing the result of the search operation. It contains a [UserSearchResult] on success or an error message on failure.
     */
    suspend fun searchUsers(query: String, page: Int): Result<UserSearchResult>
}