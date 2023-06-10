package com.example.payconiqchallenge.presentation.usersearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payconiqchallenge.R
import com.example.payconiqchallenge.data.repository.Result
import com.example.payconiqchallenge.domain.interactor.UserInteractor
import com.example.payconiqchallenge.presentation.model.UserSearchState
import com.example.payconiqchallenge.provider.StringResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserSearchViewModel(
    private val userInteractor: UserInteractor,
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {

    private val _userSearchState: MutableStateFlow<UserSearchState> =
        MutableStateFlow(UserSearchState())
    val userSearchState: StateFlow<UserSearchState> = _userSearchState

    private fun searchUsers(query: String) {

        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    runCatching {
                        userInteractor.searchUser(query)
                    }.getOrElse {
                        Result.Error(
                            stringResourceProvider.getString(R.string.user_search_error_message)
                                .format(it.message)
                        )
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

    fun onSearchTextChanged(query: String) {

        // Update the state to show loading indicator
        _userSearchState.value = userSearchState.value.copy(
            searchQuery = query,
            isLoading = true,
            error = ""
        )

        searchUsers(query)
    }

    fun onClearClick() {
        _userSearchState.value = UserSearchState()
    }
}