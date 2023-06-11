package com.example.payconiqchallenge.presentation.model

import com.example.payconiqchallenge.domain.model.UserRepositoryResult

/**
 * Data class representing the state of the user repository screen.
 *
 * @property isLoading Indicates if the screen is currently in a loading state.
 * @property userRepository The list of user repositories.
 * @property error The error message, if any.
 */
data class UserRepositoryState(
    val isLoading: Boolean = false,
    val userRepository: List<UserRepositoryResult> = emptyList(),
    val error: String = ""
) {

    companion object {
        val Empty = UserRepositoryState()
    }

}