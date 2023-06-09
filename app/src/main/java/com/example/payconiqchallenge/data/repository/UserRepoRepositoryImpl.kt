package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.data.apiservice.ApiService
import com.example.payconiqchallenge.data.model.UserRepositoryResponse
import com.example.payconiqchallenge.domain.model.UserRepositoryResult
import java.lang.Exception

class UserRepoRepositoryImpl(private val apiService: ApiService) : UserRepoRepository {

    override suspend fun getUserRepository(username: String): Result<UserRepositoryResult> {

        try {
            val userRepositoryResponse = apiService.getUserRepository(username)
            return Result.Success(mapUserRepoDataToDomain(userRepositoryResponse))
        } catch (e: Exception) {
            return Result.Error("Failed to Fetch user Repository: ${e.message}")
        }
    }

    private fun mapUserRepoDataToDomain(userRepositoryResponse: UserRepositoryResponse): UserRepositoryResult {

        return UserRepositoryResult(
            name = userRepositoryResponse.name,
            id = userRepositoryResponse.id,
            full_name = userRepositoryResponse.full_name,
            html_url = userRepositoryResponse.html_url,
            description = userRepositoryResponse.description
        )
    }
}