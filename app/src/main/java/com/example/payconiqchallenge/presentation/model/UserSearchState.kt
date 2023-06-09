package com.example.payconiqchallenge.presentation.model

import com.example.payconiqchallenge.domain.model.UserModel

data class UserSearchState(
    val searchQuery: String = "",
    val searchResults: List<UserModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
){

    companion object {
        val Empty = UserSearchState()
    }

}