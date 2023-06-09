package com.example.payconiqchallenge.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payconiqchallenge.data.repository.Result
import com.example.payconiqchallenge.data.repository.UserRepoRepository
import com.example.payconiqchallenge.domain.interactor.UserDetailInteractor
import com.example.payconiqchallenge.domain.interactor.UserInteractor
import com.example.payconiqchallenge.presentation.model.UserDetailState
import com.example.payconiqchallenge.presentation.model.UserRepositoryState
import com.example.payconiqchallenge.presentation.model.UserSearchState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserDetailViewModel(
    private val userDetailInteractor: UserDetailInteractor
) : ViewModel() {
    private val _userDetailState: MutableStateFlow<UserDetailState> =
        MutableStateFlow(UserDetailState())
    val userDetailState: StateFlow<UserDetailState> = _userDetailState


    private val _userRepositoryState: MutableStateFlow<UserRepositoryState> =
        MutableStateFlow(UserRepositoryState())
    val userRepositoryState: StateFlow<UserRepositoryState> = _userRepositoryState


    fun fetchUserDetail(userName: String) {

        // Update the state to show loading indicator
        _userDetailState.value = userDetailState.value.copy(
            isLoading = true, error = ""
        )

        viewModelScope.launch {
            try {

                val userDetailResult = withContext(Dispatchers.IO) {
                    runCatching {
                        userDetailInteractor.userDetail(userName)
                    }.getOrElse {
                        Result.Error("An error occurred while searching users: ${it.message}")
                    }
                }

                when (userDetailResult) {

                    is Result.Success -> {
                        _userDetailState.value = userDetailState.value.copy(
                            userDetail = userDetailResult.data, isLoading = false
                        )
                    }

                    is Result.Error -> {
                        _userDetailState.value = userDetailState.value.copy(
                            userDetail = null, isLoading = false, error = userDetailResult.message
                        )
                    }
                }

            } catch (e: Exception) {
                _userDetailState.value = userDetailState.value.copy(
                    userDetail = null, isLoading = false, error = e.message!!
                )
            }
        }
    }


    fun fetchUserRepository(userName: String) {

        // Update the state to show loading indicator
        _userRepositoryState.value = userRepositoryState.value.copy(
            isLoading = true, error = ""
        )

        viewModelScope.launch {
            try {

                val userRepositoryResult = withContext(Dispatchers.IO) {
                    runCatching {
                        userDetailInteractor.userRepository(userName)
                    }.getOrElse {
                        Result.Error("An error occurred while searching users: ${it.message}")
                    }
                }

                when (userRepositoryResult) {

                    is Result.Success -> {
                        _userRepositoryState.value = userRepositoryState.value.copy(
                            userRepository = userRepositoryResult.data, isLoading = false
                        )
                    }

                    is Result.Error -> {
                        _userRepositoryState.value = userRepositoryState.value.copy(
                            userRepository = emptyList(),
                            isLoading = false,
                            error = userRepositoryResult.message
                        )
                    }
                }

            } catch (e: Exception) {
                _userRepositoryState.value = userRepositoryState.value.copy(
                    userRepository = emptyList(), isLoading = false, error = e.message!!
                )
            }
        }
    }

}