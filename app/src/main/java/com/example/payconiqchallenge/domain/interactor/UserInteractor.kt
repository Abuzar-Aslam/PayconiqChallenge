package com.example.payconiqchallenge.domain.interactor

import com.example.payconiqchallenge.data.repository.UserSearchRepository
import com.example.payconiqchallenge.domain.model.UserSearchResult
import com.example.payconiqchallenge.data.repository.Result

/**
 * Interactor class responsible for searching users.
 *
 * @param userRepository The repository for searching users.
 */
class UserInteractor(private val userRepository: UserSearchRepository) {

    /**
     * Searches users based on the provided query and page number.
     *
     * @param query The search query.
     * @param page The page number of the search results.
     * @return A [Result] object representing the result of the search operation. It contains a [UserSearchResult] on success or an error message on failure.
     */
    suspend fun searchUser(query: String, page: Int): Result<UserSearchResult> {
        return userRepository.searchUsers(query, page)
    }
}