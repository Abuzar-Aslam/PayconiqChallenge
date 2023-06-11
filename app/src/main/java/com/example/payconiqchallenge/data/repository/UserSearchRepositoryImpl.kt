package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.data.model.UserItemResponse
import com.example.payconiqchallenge.data.model.UserSearchResponse
import com.example.payconiqchallenge.domain.model.UserModel
import com.example.payconiqchallenge.domain.model.UserSearchResult
import com.example.payconiqchallenge.data.apiservice.ApiService

/**
 * Implementation of the [UserSearchRepository] interface that performs user search operations.
 *
 * @property apiService The ApiService instance used for making API calls.
 */
class UserSearchRepositoryImpl(private val apiService: ApiService) : UserSearchRepository {

    /**
     * Searches for users based on the provided query.
     *
     * @param query The search query.
     * @return A [Result] object representing the result of the search operation. It contains a [UserSearchResult] on success or an error message on failure.
     */
    override suspend fun searchUsers(query: String, page: Int): Result<UserSearchResult> {
        return try {
            val userResponseData = apiService.searchUsers(query,page)
            val userSearchResult = mapUserDataToDomain(userResponseData)
            Result.Success(userSearchResult)
        } catch (e: Exception) {
           Result.Error("Failed to fetch users: ${e.message}")
        }
    }

    /**
     * Maps the user search response data to the domain model.
     *
     * @param userSearchResponse The user search response from the API.
     * @return A [UserSearchResult] containing the mapped domain model.
     */
    private fun mapUserDataToDomain(userSearchResponse: UserSearchResponse): UserSearchResult {
        return UserSearchResult(
            totalCount = userSearchResponse.totalCount,
            users = userSearchResponse.items.map { mapUserItemToDomain(it) }
        )
    }

    /**
     * Maps a user item response to the domain model.
     *
     * @param userItemResponse The user item response from the API.
     * @return A [UserModel] containing the mapped domain model.
     */
    private fun mapUserItemToDomain(userItemResponse: UserItemResponse): UserModel {
        return UserModel(
            login = userItemResponse.login,
            id = userItemResponse.id,
            avatarUrl = userItemResponse.avatarUrl,
            htmlUrl = userItemResponse.htmlUrl
        )
    }


}

