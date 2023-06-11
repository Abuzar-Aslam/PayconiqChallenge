package com.example.payconiqchallenge.domain.interactor

import com.example.payconiqchallenge.data.repository.UserSearchRepository
import com.example.payconiqchallenge.domain.model.UserSearchResult
import com.example.payconiqchallenge.data.repository.Result
import com.example.payconiqchallenge.domain.model.UserDetailResult

/**
 * Interactor class responsible for searching users.
 *
 * @param userRepository The repository for searching users.
 */
class UserInteractor(private val userRepository: UserSearchRepository) {

    /**
     * Searches users based on the provided query.
     *
     * @param query The search query.
     * @return A Result object representing the success or failure of the search operation,
     * along with the search result data if successful.
     */
    suspend fun searchUser(query: String): Result<UserSearchResult> {
        return userRepository.searchUsers(query)
    }
}