package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.domain.model.UserDetailResult
import com.example.payconiqchallenge.domain.model.UserSearchResult

interface UserRepository {

    suspend fun searchUsers(query: String): Result<UserSearchResult>
    suspend fun getUserDetail(username: String): Result<UserDetailResult>

}