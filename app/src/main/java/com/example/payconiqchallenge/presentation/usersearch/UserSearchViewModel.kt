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

/**
 *ViewModel class for the User Search feature.
 *@param userInteractor The UserInteractor instance responsible for user-related operations.
 *@param stringResourceProvider The StringResourceProvider instance for retrieving string resources.
 */
class UserSearchViewModel(
    private val userInteractor: UserInteractor,
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {

    private val _userSearchState: MutableStateFlow<UserSearchState> =
        MutableStateFlow(UserSearchState())
    val userSearchState: StateFlow<UserSearchState> = _userSearchState

    /**
     *Searches for users based on the given query.
     *@param query The search query entered by the user.
     */
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

    /**
     *Callback function invoked when the search text is changed.
     *@param query The updated search query entered by the user.
     */
    fun onSearchTextChanged(query: String) {

        // Update the state to show loading indicator
        _userSearchState.value = userSearchState.value.copy(
            searchQuery = query,
            isLoading = true,
            error = ""
        )

        searchUsers(query)
    }

    /**
     *Callback function invoked when the clear button is clicked.
     *Resets the search state to its initial values.
     */
    fun onClearClick() {
        _userSearchState.value = UserSearchState()
    }
}