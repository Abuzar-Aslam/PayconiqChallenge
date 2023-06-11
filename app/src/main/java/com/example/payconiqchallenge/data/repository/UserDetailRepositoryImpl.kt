package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.data.apiservice.ApiService
import com.example.payconiqchallenge.data.model.UserDetailResponse
import com.example.payconiqchallenge.domain.model.UserDetailResult
import java.lang.Exception

/**
 * Implementation of [UserDetailRepository] that retrieves user details from the API service.
 *
 * @param apiService The API service instance used for making network requests.
 */
class UserDetailRepositoryImpl(private val apiService: ApiService) : UserDetailRepository {

    /**
     * Retrieves the user detail for the specified username.
     *
     * @param username The username of the user.
     * @return A [Result] object representing the result of the operation. It contains either a [UserDetailResult] on success or an error message on failure.
     */
    override suspend fun getUserDetail(username: String): Result<UserDetailResult> {

        try {
            val userDetail = apiService.getUserDetail(username)
            return Result.Success(mapUserDetailDataToDomain(userDetail))
        } catch (e: Exception) {
            return Result.Error("Failed to Fetch user Repository: ${e.message}")
        }
    }

    /**
     * Maps the user detail response from the API to the domain model.
     *
     * @param userDetailResponse The user detail response received from the API.
     * @return The mapped [UserDetailResult] domain model.
     */
    private fun mapUserDetailDataToDomain(userDetailResponse: UserDetailResponse): UserDetailResult {

        return UserDetailResult(
            name = userDetailResponse.name,
            login = userDetailResponse.login,
            id = userDetailResponse.id,
            avatarUrl = userDetailResponse.avatarUrl,
            following = userDetailResponse.following,
            followers = userDetailResponse.followers
        )
    }
}