package com.example.payconiqchallenge.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payconiqchallenge.data.repository.Result
import com.example.payconiqchallenge.domain.interactor.UserInteractor
import com.example.payconiqchallenge.presentation.model.UserSearchState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserSearchViewModel(private val userInteractor: UserInteractor) : ViewModel() {


    private val _userSearchState: MutableStateFlow<UserSearchState> =
        MutableStateFlow(UserSearchState())
    val userSearchState: StateFlow<UserSearchState> = _userSearchState

    fun searchUsers(query: String) {

        // Update the state to show loading indicator
        _userSearchState.value = userSearchState.value.copy(
            searchQuery = query,
            isLoading = true,
            error = ""
        )

        viewModelScope.launch {
            try {

                val result = withContext(Dispatchers.IO) {
                    runCatching {
                        userInteractor.searchUser(query)
                    }.getOrElse {
                        Result.Error("An error occurred while searching users: ${it.message}")
                    }
                }

                when (result) {

                    is Result.Success -> {
                        _userSearchState.value = userSearchState.value.copy(
                            searchResults = result.data.users,
                            isLoading = false
                        )
                    }

                    is Result.Error -> {
                        _userSearchState.value = userSearchState.value.copy(
                            searchResults = emptyList(),
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

            } catch (e: Exception) {
                _userSearchState.value = userSearchState.value.copy(
                    searchResults = emptyList(),
                    isLoading = false,
                    error = e.message!!
                )
            }
        }
    }

    fun onSearchTextChanged(it: String) {
        searchUsers(it)
    }

    fun onClearClick() {
        _userSearchState.value = UserSearchState()
    }
}