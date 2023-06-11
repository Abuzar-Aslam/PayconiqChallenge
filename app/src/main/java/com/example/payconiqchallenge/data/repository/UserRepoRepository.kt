package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.domain.model.UserRepositoryResult

/**
 * Repository interface for retrieving user repositories.
 */
interface UserRepoRepository {

    /**
     * Retrieves the repositories of a user with the specified username.
     *
     * @param username The username of the user.
     * @return A [Result] object representing the result of the operation. It contains either a list of [UserRepositoryResult] on success or an error message on failure.
     */
    suspend fun getUserRepository(username: String): Result<List<UserRepositoryResult>>

}