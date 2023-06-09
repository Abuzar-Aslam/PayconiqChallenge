package com.example.payconiqchallenge.domain.interactor

import com.example.payconiqchallenge.data.repository.Result
import com.example.payconiqchallenge.data.repository.UserDetailRepository
import com.example.payconiqchallenge.data.repository.UserRepoRepository
import com.example.payconiqchallenge.domain.model.UserDetailResult
import com.example.payconiqchallenge.domain.model.UserRepositoryResult

class UserDetailInteractor(
    private val userDetailRepository: UserDetailRepository,
    private val userRepoRepository: UserRepoRepository
) {

    suspend fun userDetail(userName: String): Result<UserDetailResult> {
        return userDetailRepository.getUserDetail(userName)
    }

    suspend fun userRepository(userName: String): Result<UserRepositoryResult> {
        return userRepoRepository.getUserRepository(userName)
    }
}