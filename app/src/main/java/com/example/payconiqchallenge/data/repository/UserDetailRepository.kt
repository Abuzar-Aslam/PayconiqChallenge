package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.domain.model.UserDetailResult
import com.example.payconiqchallenge.domain.model.UserRepositoryResult

/**
 * Interface defining the contract for retrieving user details.
 */
interface UserDetailRepository {

    /**
     * Retrieves the user detail for the specified username.
     *
     * @param username The username of the user.
     * @return A [Result] object representing the result of the operation. It contains either a [UserDetailResult] on success or an error message on failure.
     */
    suspend fun getUserDetail(username: String): Result<UserDetailResult>

}