package com.example.payconiqchallenge.presentation.model

import com.example.payconiqchallenge.domain.model.UserModel

/**
 * Data class representing the state of the user search screen.
 *
 * @property searchQuery The current search query entered by the user.
 * @property searchResults The list of user search results.
 * @property isLoading Indicates if the screen is currently in a loading state.
 * @property error The error message, if any.
 */
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