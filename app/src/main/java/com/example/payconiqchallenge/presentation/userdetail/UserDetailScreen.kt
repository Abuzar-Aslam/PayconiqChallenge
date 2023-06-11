package com.example.payconiqchallenge.presentation.userdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.payconiqchallenge.domain.model.UserDetailResult
import com.example.payconiqchallenge.domain.model.UserRepositoryResult
import com.example.payconiqchallenge.presentation.model.UserDetailState
import com.example.payconiqchallenge.presentation.model.UserRepositoryState
import com.example.payconiqchallenge.utils.rememberFlowWithLifecycle
import androidx.compose.ui.res.stringResource
import com.example.payconiqchallenge.R
import com.example.payconiqchallenge.utils.LoadingIndicator


@Composable
fun UserDetailUI(userDetailViewModel: UserDetailViewModel, navHostController: NavHostController,selectedUserName:String) {


    val userDetailState by rememberFlowWithLifecycle(userDetailViewModel.userDetailState)
        .collectAsState(initial = UserDetailState.Empty)

    val userRepositoryState by rememberFlowWithLifecycle(userDetailViewModel.userRepositoryState)
        .collectAsState(initial = UserRepositoryState.Empty)

    val userDetailTitle = stringResource(id = R.string.user_detail_title)

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = userDetailTitle)
        }, navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    modifier = Modifier,
                    contentDescription = stringResource(id = R.string.user_detail_back)
                )

            }
        })
    }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = it)
        ) {
            UserDetailContent(userDetailState.userDetail)
            Divider()
            UserRepositoryList(userRepositoryState.userRepository)
        }

        LoadingIndicator(userDetailState.isLoading || userRepositoryState.isLoading)
    }

    // Fetch user detail and repository data
    LaunchedEffect(Unit) {
        userDetailViewModel.fetchUserDetail(selectedUserName)
        userDetailViewModel.fetchUserRepository(selectedUserName)
    }
}

@Composable
fun UserRepositoryList(userRepositoryResult: List<UserRepositoryResult>) {
    val noRepositoryErrorMessage = stringResource(R.string.user_detail_no_repository_found)
    if (userRepositoryResult.isNotEmpty()) {
        LazyColumn {
            items(userRepositoryResult.size) { repository ->
                RepositoryItem(userRepositoryResult[repository])
            }
        }
    } else {
        // Handle the case when repositories list is empty (e.g., data is still loading or API call failed)
        Text(
            text = noRepositoryErrorMessage,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )
    }

}

@Composable
fun UserDetailContent(userDetailResult: UserDetailResult?) {
    val followersText = stringResource(R.string.user_detail_followers)
    val followingText = stringResource(R.string.user_detail_following)
    val dataUnavailable = stringResource(R.string.user_detail_data_unavailable)
    val userIcon = stringResource(id = R.string.user_detail_user_icon_description)

    if (userDetailResult != null) {
        Column(
            modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = userDetailResult.avatarUrl,
                    contentDescription = userIcon,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = userDetailResult.name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Text(
                text = followersText + userDetailResult.followers,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = followingText + userDetailResult.following,
                style = MaterialTheme.typography.body1
            )
        }
    } else {
        // Handle the case when userDetail is null (e.g., data is still loading or API call failed)
        Text(
            text = dataUnavailable,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )
    }
}



