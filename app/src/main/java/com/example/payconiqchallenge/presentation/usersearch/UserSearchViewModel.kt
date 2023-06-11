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

    private var currentPage: Int = 1
    private var totalCount: Int = 0

    /**
     *Searches for users based on the given query.
     *@param query The search query entered by the user.
     */
    private fun searchUsers(query: String, page: Int) {

        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    runCatching {
                        userInteractor.searchUser(query, page)

                    }.getOrElse {
                        Result.Error(
                            stringResourceProvider.getString(R.string.user_search_error_message)
                                .format(it.message)
                        )
                    }
                }

                when (result) {
                    is Result.Success -> {

                        val userList = result.data.users
                        totalCount = result.data.totalCount

                        if (userList.isEmpty() && currentPage == 1) {
                            // No results found on the first page
                            _userSearchState.value = userSearchState.value.copy(
                                searchResults = emptyList(),
                                isLoading = false,
                                error = stringResourceProvider.getString(R.string.no_search_message)
                            )
                        } else {
                            // Results found, update the state
                            val updatedSearchResults = if (currentPage == 1) {
                                userList
                            } else {
                                userSearchState.value.searchResults + userList
                            }

                            _userSearchState.value = userSearchState.value.copy(
                                searchResults = updatedSearchResults,
                                isLoading = false,
                                error = ""
                            )
                        }
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
        currentPage = 1
        _userSearchState.value = UserSearchState(searchQuery = query)

        if (query.isNotEmpty()) {
            // Start searching with the first page
            _userSearchState.value = userSearchState.value.copy(
                isLoading = true,
                error = ""
            )
            searchUsers(query, currentPage)
        }
    }

    fun onLoadMore(query: String) {
        val query = userSearchState.value.searchQuery
        if (query.isNotEmpty() && userSearchState.value.searchResults.size < totalCount) {
            // Increment the page count and load the next page
            currentPage++
            searchUsers(query, currentPage)
        }
    }

    /**
     *Callback function invoked when the clear button is clicked.
     *Resets the search state to its initial values.
     */
    fun onClearClick() {
        // Clear the search query and results
        currentPage = 1
        totalCount = 0
        _userSearchState.value = UserSearchState()
    }
}