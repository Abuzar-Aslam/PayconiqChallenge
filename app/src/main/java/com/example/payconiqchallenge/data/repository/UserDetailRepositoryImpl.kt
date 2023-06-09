package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.data.apiservice.ApiService
import com.example.payconiqchallenge.data.model.UserRepositoryResponse
import com.example.payconiqchallenge.data.model.UserDetailResponse
import com.example.payconiqchallenge.domain.model.UserDetailResult
import com.example.payconiqchallenge.domain.model.UserRepositoryResult
import java.lang.Exception

class UserDetailRepositoryImpl(private val apiService: ApiService) : UserDetailRepository {

    override suspend fun getUserDetail(username: String): Result<UserDetailResult> {

        try {
            val userDetail = apiService.getUserDetail(username)
            return Result.Success(mapUserDetailDataToDomain(userDetail))
        } catch (e: Exception) {
            return Result.Error("Failed to Fetch user Repository: ${e.message}")
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


}