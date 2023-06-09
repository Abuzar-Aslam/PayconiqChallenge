package com.example.payconiqchallenge.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payconiqchallenge.data.repository.Result
import com.example.payconiqchallenge.domain.interactor.UserInteractor
import com.example.payconiqchallenge.presentation.model.UserDetailState
import com.example.payconiqchallenge.presentation.model.UserSearchState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserDetailViewModel(private val userInteractor: UserInteractor) : ViewModel() {
    private val _userDetailState: MutableStateFlow<UserDetailState> =
        MutableStateFlow(UserDetailState())
    val userDetailState: StateFlow<UserDetailState> = _userDetailState

    fun fetchUserDetail(userName: String) {

        // Update the state to show loading indicator
        _userDetailState.value = userDetailState.value.copy(
            isLoading = true,
            error = ""
        )

        viewModelScope.launch {
            try {

                val result = withContext(Dispatchers.IO) {
                    runCatching {
                        userInteractor.userDetail(userName)
                    }.getOrElse {
                        Result.Error("An error occurred while searching users: ${it.message}")
                    }
                }

                when (result) {

                    is Result.Success -> {
                        _userDetailState.value = userDetailState.value.copy(
                            userDetail = result.data,
                            isLoading = false
                        )
                    }

                    is Result.Error -> {
                        _userDetailState.value = userDetailState.value.copy(
                            userDetail = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

            } catch (e: Exception) {
                _userDetailState.value = userDetailState.value.copy(
                    userDetail = null,
                    isLoading = false,
                    error = e.message!!
                )
            }
        }
    }

}