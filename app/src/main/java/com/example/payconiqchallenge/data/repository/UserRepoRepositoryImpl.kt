package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.data.apiservice.ApiService
import com.example.payconiqchallenge.data.model.UserRepositoryResponse
import com.example.payconiqchallenge.domain.model.UserRepositoryResult
import java.lang.Exception

class UserRepoRepositoryImpl(private val apiService: ApiService) : UserRepoRepository {

    override suspend fun getUserRepository(username: String): Result<List<UserRepositoryResult>> {

        return try {
            val userRepositoryResponse = apiService.getUserRepository(username)
            Result.Success(mapUserRepoDataToDomain(userRepositoryResponse))
        } catch (e: Exception) {
            Result.Error("Failed to Fetch user Repository: ${e.message}")
        }
    }

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