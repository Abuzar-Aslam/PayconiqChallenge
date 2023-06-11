package com.example.payconiqchallenge.domain.interactor

import com.example.payconiqchallenge.data.repository.Result
import com.example.payconiqchallenge.data.repository.UserDetailRepository
import com.example.payconiqchallenge.data.repository.UserRepoRepository
import com.example.payconiqchallenge.domain.model.UserDetailResult
import com.example.payconiqchallenge.domain.model.UserRepositoryResult

/**
 * Interactor class responsible for retrieving user details and user repositories.
 *
 * @param userDetailRepository The repository for retrieving user details.
 * @param userRepoRepository The repository for retrieving user repositories.
 */
class UserDetailInteractor(
    private val userDetailRepository: UserDetailRepository,
    private val userRepoRepository: UserRepoRepository
) {

    /**
     * Retrieves the user details for the given username.
     *
     * @param userName The username of the user.
     * @return A Result object representing the success or failure of the operation,
     * along with the user detail data if successful.
     */
    suspend fun userDetail(userName: String): Result<UserDetailResult> {
        return userDetailRepository.getUserDetail(userName)
    }

    /**
     * Retrieves the user repositories for the given username.
     *
     * @param userName The username of the user.
     * @return A Result object representing the success or failure of the operation,
     * along with the user repository data if successful.
     */
    suspend fun userRepository(userName: String): Result<List<UserRepositoryResult>> {
        return userRepoRepository.getUserRepository(userName)
    }
}