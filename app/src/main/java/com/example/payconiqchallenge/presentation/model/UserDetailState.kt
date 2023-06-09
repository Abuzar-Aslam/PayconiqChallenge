package com.example.payconiqchallenge.presentation.model

import com.example.payconiqchallenge.domain.model.UserDetailResult

data class UserDetailState(
    val isLoading: Boolean = false,
    val userDetail: UserDetailResult? = null,
    val userRepository :
    val error: String = ""
){

    companion object {
        val Empty = UserDetailState()
    }

}