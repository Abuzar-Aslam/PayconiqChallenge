package com.example.payconiqchallenge.presentation.model

import com.example.payconiqchallenge.domain.model.UserDetailResult

/**
 * Data class representing the state of the user detail screen.
 *
 * @property isLoading Indicates if the screen is currently in a loading state.
 * @property userDetail The user detail information.
 * @property error The error message, if any.
 */
data class UserDetailState(
    val isLoading: Boolean = false,
    val userDetail: UserDetailResult? = null,
    val error: String = ""
){

    companion object {
        val Empty = UserDetailState()
    }

}