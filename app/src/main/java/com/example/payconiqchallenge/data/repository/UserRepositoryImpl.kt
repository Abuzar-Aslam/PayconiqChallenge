package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.data.model.UserItemResponse
import com.example.payconiqchallenge.data.model.UserSearchResponse
import com.example.payconiqchallenge.domain.model.UserDetailResult
import com.example.payconiqchallenge.domain.model.UserModel
import com.example.payconiqchallenge.domain.model.UserSearchResult
import com.example.payconiqchallenge.data.apiservice.ApiService
import com.example.payconiqchallenge.data.model.UserDetailResponse

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

    override suspend fun getUserDetail(userName: String): Result<UserDetailResult> {
        return try {
            val userDetailResponseData = apiService.getUserDetail(userName)
            val userSearchResult = mapUserDetailDataToDomain(userDetailResponseData)
            Result.Success(userSearchResult)
        } catch (e: Exception) {
            Result.Error("Failed to fetch users: ${e.message}")
        }
    }

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

