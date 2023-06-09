package com.example.payconiqchallenge.domain.interactor

import com.example.payconiqchallenge.data.repository.UserRepository
import com.example.payconiqchallenge.domain.model.UserSearchResult
import com.example.payconiqchallenge.data.repository.Result
import com.example.payconiqchallenge.domain.model.UserDetailResult

class UserInteractor(private val userRepository: UserRepository) {

    suspend fun searchUser(query : String): Result<UserSearchResult> {
        return userRepository.searchUsers(query)
    }

    suspend fun userDetail(userName:String) : Result<UserDetailResult>{
        return  userRepository.getUserDetail(userName)
    }

}