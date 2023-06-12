package com.example.payconiqchallenge.presentation.usersearch

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.payconiqchallenge.domain.model.UserModel
import com.example.payconiqchallenge.navigation.NavPath
import com.example.payconiqchallenge.presentation.model.UserSearchState
import com.example.payconiqchallenge.presentation.searchbar.SearchBarUI
import com.example.payconiqchallenge.utils.LoadingIndicator
import com.example.payconiqchallenge.utils.rememberFlowWithLifecycle

/**
 * Composable function for the User Search UI screen.
 *
 * @param userSearchViewModel The UserSearchViewModel instance responsible for handling user search logic.
 * @param navHostController The NavHostController used for navigation within the app.
 */
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun UserSearchScreen(
    userSearchViewModel: UserSearchViewModel,
    navHostController: NavHostController
) {

    // Collect the user search state using rememberFlowWithLifecycle and collectAsState
    val userSearchModelState by rememberFlowWithLifecycle(userSearchViewModel.userSearchState)
        .collectAsState(initial = UserSearchState.Empty)

    // Render the search bar and user list
    SearchBarUI(
        searchText = userSearchModelState.searchQuery,
        placeholderText = "Search users",
        onSearchTextChanged = { userSearchViewModel.onSearchTextChanged(it) },
        onClearClick = { userSearchViewModel.onClearClick() },
        matchesFound = userSearchModelState.searchResults.isNotEmpty(),
        errorMessage = userSearchModelState.error
    ) {
        // Render loading indicator and user list
        LoadingIndicator(isLoading = userSearchModelState.isLoading)
        UserList(
            users = userSearchModelState.searchResults,
            onClick = { user ->
                navHostController.navigate(route = "${NavPath.UserDetail.route}?name=${user.login}")
            },
            onLoadMore = { userSearchViewModel.onLoadMore() }
        )
    }
}

/**
 * Composable function for rendering a list of users.
 *
 * @param users The list of UserModel representing the users to display.
 * @param onClick Callback function invoked when a user item is clicked.
 * @param onLoadMore Callback function invoked when the end of the list is reached.
 */
@Composable
fun UserList(
    users: List<UserModel>?,
    onClick: (UserModel) -> Unit,
    onLoadMore: () -> Unit
) {

    val scrollState = rememberLazyListState()


    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (!users.isNullOrEmpty())
            items(users.size) { index ->
                val user = users[index]
                UserItem(user) {
                    onClick(user)
                }
                if (index == users.size - 1) {
                    LaunchedEffect(scrollState.layoutInfo.totalItemsCount) {
                        if (scrollState.firstVisibleItemIndex + scrollState.layoutInfo.visibleItemsInfo.size >= scrollState.layoutInfo.totalItemsCount) {
                            onLoadMore()
                        }
                    }
                }
            }
    }
}
