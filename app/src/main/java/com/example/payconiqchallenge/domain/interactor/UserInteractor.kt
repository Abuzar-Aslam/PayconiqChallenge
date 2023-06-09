package com.example.payconiqchallenge.domain.interactor

import com.example.payconiqchallenge.data.repository.UserSearchRepository
import com.example.payconiqchallenge.domain.model.UserSearchResult
import com.example.payconiqchallenge.data.repository.Result
import com.example.payconiqchallenge.domain.model.UserDetailResult

class UserInteractor(private val userRepository: UserSearchRepository) {

    suspend fun searchUser(query: String): Result<UserSearchResult> {
        return userRepository.searchUsers(query)
    }
}