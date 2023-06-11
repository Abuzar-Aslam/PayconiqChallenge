package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.domain.model.UserDetailResult
import com.example.payconiqchallenge.domain.model.UserSearchResult

/**
 * Repository interface for searching users.
 */
interface UserSearchRepository {

    /**
     * Searches for users based on the provided query.
     *
     * @param query The search query.
     * @return A [Result] object representing the result of the search operation. It contains a [UserSearchResult] on success or an error message on failure.
     */
    suspend fun searchUsers(query: String): Result<UserSearchResult>

}