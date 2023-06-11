package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.data.apiservice.ApiService
import com.example.payconiqchallenge.data.model.UserRepositoryResponse
import com.example.payconiqchallenge.domain.model.UserRepositoryResult
import java.lang.Exception

/**
 * Implementation of the [UserRepoRepository] interface.
 * Fetches user repositories from the API service.
 *
 * @param apiService The API service used to fetch user repositories.
 */
class UserRepoRepositoryImpl(private val apiService: ApiService) : UserRepoRepository {

    /**
     * Retrieves the repositories of a user with the specified username.
     *
     * @param username The username of the user.
     * @return A [Result] object representing the result of the operation. It contains either a list of [UserRepositoryResult] on success or an error message on failure.
     */
    override suspend fun getUserRepository(username: String): Result<List<UserRepositoryResult>> {

        return try {
            val userRepositoryResponse = apiService.getUserRepository(username)
            Result.Success(mapUserRepoDataToDomain(userRepositoryResponse))
        } catch (e: Exception) {
            Result.Error("Failed to Fetch user Repository: ${e.message}")
        }
    }

    /**
     * Maps the API response of user repositories to the corresponding domain models.
     *
     * @param userRepositoryResponse The response from the API service.
     * @return A list of [UserRepositoryResult] domain models.
     */
    private fun mapUserRepoDataToDomain(userRepositoryResponse: List<UserRepositoryResponse>): List<UserRepositoryResult> {

        return userRepositoryResponse.map { response ->
            UserRepositoryResult(
                name = response.name,
                id = response.id,
                starCount = response.starCount,
                watchCount = response.watchCount,
                description = response.description
            )
        }
    }
}