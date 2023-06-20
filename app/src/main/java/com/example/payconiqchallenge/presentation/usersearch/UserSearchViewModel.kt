package com.example.payconiqchallenge.presentation.usersearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.payconiqchallenge.R
import com.example.payconiqchallenge.data.repository.Result
import com.example.payconiqchallenge.domain.interactor.UserInteractor
import com.example.payconiqchallenge.presentation.model.UserSearchState
import com.example.payconiqchallenge.provider.StringResourceProvider
import com.example.payconiqchallenge.utils.Constants.DEFAULT_PAGE
import com.example.payconiqchallenge.utils.Constants.PER_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel class for the User Search feature.
 * @param userInteractor The UserInteractor instance responsible for user-related operations.
 * @param stringResourceProvider The StringResourceProvider instance for retrieving string resources.
 */
class UserSearchViewModel(
    private val userInteractor: UserInteractor,
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {

    private val _userSearchState: MutableStateFlow<UserSearchState> =
        MutableStateFlow(UserSearchState())
    val userSearchState: MutableStateFlow<UserSearchState> = _userSearchState

    var currentPage: Int = DEFAULT_PAGE
    var totalCount: Int = 0

    /**
     * Searches for users based on the given query and page number.
     * @param query The search query entered by the user.
     * @param page The page number to retrieve.
     */
    fun searchUsers(query: String, page: Int) {
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
                                error = "",
                                totalPageCount = totalCount
                            )
                        }
                    }

                    is Result.Error -> {
                        _userSearchState.value = userSearchState.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } catch (e: Exception) {
                _userSearchState.value = userSearchState.value.copy(
                    searchResults = emptyList(),
                    isLoading = false,
                    error = e.message ?: ""
                )
            }
        }
    }

    /**
     * Callback function invoked when the search text is changed.
     * @param query The updated search query entered by the user.
     */
    fun onSearchTextChanged(query: String) {
        currentPage = DEFAULT_PAGE
        if (query.isNotEmpty() && isNameValid(query)) {
            // Start searching with the first page
            _userSearchState.value = userSearchState.value.copy(
                searchQuery = query,
                isLoading = true,
                error = ""
            )
            searchUsers(query, currentPage)
        } else {
            _userSearchState.value = UserSearchState(searchQuery = query)
        }
    }

    /**
     * Callback function invoked when

    the "Load More" action is triggered. It loads the next page of search results if there are more pages available.
     */
    fun onLoadMore() {
        val query = userSearchState.value.searchQuery
        if (query.isNotEmpty() && currentPage < getTotalPageCount()) {
            // Increment the page count and load the next page
            currentPage++
            searchUsers(query, currentPage)
        }
    }


    /**
     * Callback function invoked when the clear button is clicked.
     * Resets the search state to its initial values.
     */
    fun onClearClick() {
        // Clear the search query and results
        currentPage = DEFAULT_PAGE
        totalCount = 0
        _userSearchState.value = UserSearchState()
    }

    /**
     * Calculates the total page count based on the total items and the items per page.
     * @return The total page count.
     */
    private fun getTotalPageCount(): Int {
        val perPage = PER_PAGE
        return if (totalCount % perPage == 0) {
            totalCount / perPage
        } else {
            (totalCount / perPage) + 1
        }
    }

    /**
     *Checks if a given name is valid according to the specified criteria.
     * @param name The name to be validated.
     *@return true if the name is valid, false otherwise.
     */
    fun isNameValid(name: String): Boolean {
        // Define a regular expression pattern for valid names
        val pattern = "^[a-zA-Z\\-]+$".toRegex()

        // Use the pattern to match the name against the regular expression
        return pattern.matches(name)
    }
}

