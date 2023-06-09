package com.example.payconiqchallenge.presentation.model

import com.example.payconiqchallenge.domain.model.UserDetailModel

data class UserDetailState(
    val loading: Boolean,
    val userDetail: UserDetailModel?,
    val error: String?
)