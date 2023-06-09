package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.domain.model.UserRepositoryResult

interface UserRepoRepository {

    suspend fun getUserRepository(username: String): Result<List<UserRepositoryResult>>

}