package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.domain.model.UserDetailResult
import com.example.payconiqchallenge.domain.model.UserRepositoryResult

interface UserDetailRepository {

    suspend fun getUserDetail(username: String): Result<UserDetailResult>

}