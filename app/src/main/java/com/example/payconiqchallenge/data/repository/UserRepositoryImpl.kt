package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.data.model.UserItemResponse
import com.example.payconiqchallenge.data.model.UserSearchResponse
import com.example.payconiqchallenge.domain.model.UserDetailModel
import com.example.payconiqchallenge.domain.model.UserModel
import com.example.payconiqchallenge.domain.model.UserSearchResult
import com.example.payconiqchallenge.data.apiservice.ApiService

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {


    override suspend fun searchUsers(query: String): Result<UserSearchResult> {
        return try {
            val userResponseData = apiService.searchUsers(query)
            val userSearchResult = mapUserDataToDomain(userResponseData)
            Result.Success(userSearchResult)
        } catch (e: Exception) {
            Result.Error("Failed to fetch users: ${e.message}")
        }
    }

    override suspend fun getUserDetail(username: String): UserDetailModel {
        TODO("Not yet implemented")
    }

    private fun mapUserDataToDomain(userSearchResponse: UserSearchResponse): UserSearchResult {
        return UserSearchResult(
            totalCount = userSearchResponse.totalCount,
            users = userSearchResponse.items.map { mapUserItemToDomain(it) }
        )
    }

    private fun mapUserItemToDomain(userItemResponse: UserItemResponse): UserModel {
        return UserModel(
            login = userItemResponse.login,
            id = userItemResponse.id,
            avatarUrl = userItemResponse.avatarUrl,
            htmlUrl = userItemResponse.htmlUrl
        )
    }


}

