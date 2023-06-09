package com.example.payconiqchallenge.presentation.model

import com.example.payconiqchallenge.domain.model.UserRepositoryResult

data class UserRepositoryState(
    val isLoading: Boolean = false,
    val userRepository: List<UserRepositoryResult> = emptyList(),
    val error: String = ""
) {

    companion object {
        val Empty = UserRepositoryState()
    }

}