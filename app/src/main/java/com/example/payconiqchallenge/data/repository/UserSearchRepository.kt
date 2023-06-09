package com.example.payconiqchallenge.data.repository

import com.example.payconiqchallenge.domain.model.UserDetailResult
import com.example.payconiqchallenge.domain.model.UserSearchResult

interface UserSearchRepository {

    suspend fun searchUsers(query: String): Result<UserSearchResult>

}