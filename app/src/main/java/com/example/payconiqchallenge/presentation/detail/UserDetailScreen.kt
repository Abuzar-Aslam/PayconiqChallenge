package com.example.payconiqchallenge.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.payconiqchallenge.domain.model.UserDetailResult
import com.example.payconiqchallenge.domain.model.UserRepositoryResult
import com.example.payconiqchallenge.presentation.model.UserDetailState
import com.example.payconiqchallenge.presentation.model.UserRepositoryState
import com.example.payconiqchallenge.utils.rememberFlowWithLifecycle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.payconiqchallenge.R


@Composable
fun UserDetailUI(userDetailViewModel: UserDetailViewModel, navHostController: NavHostController) {


    val userDetailState by rememberFlowWithLifecycle(userDetailViewModel.userDetailState)
        .collectAsState(initial = UserDetailState.Empty)

    val userRepositoryState by rememberFlowWithLifecycle(userDetailViewModel.userRepositoryState)
        .collectAsState(initial = UserRepositoryState.Empty)

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "User Detail")
        }, navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    modifier = Modifier,
                    contentDescription = stringResource(id = R.string.userDetail_back)
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
        userDetailViewModel.fetchUserDetail("a")
        userDetailViewModel.fetchUserRepository("b")
    }
}

@Composable
fun UserRepositoryList(userRepositoryResult: List<UserRepositoryResult>) {
    if (userRepositoryResult.isNotEmpty()) {
        LazyColumn {
            items(userRepositoryResult.size) { repository ->
                RepositoryItem(userRepositoryResult[repository])
            }
        }
    } else {
        // Handle the case when repositories list is empty (e.g., data is still loading or API call failed)
        Text(
            text = "No repositories found",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )
    }

}

@Composable
fun UserDetailContent(userDetailResult: UserDetailResult?) {
    if (userDetailResult != null) {
        Column(
            modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = userDetailResult.avatarUrl,
                    contentDescription = "User Icon",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = userDetailResult.name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Text(
                text = "Followers: ${userDetailResult.followers}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Following: ${userDetailResult.following}",
                style = MaterialTheme.typography.body1
            )
        }
    } else {
        // Handle the case when userDetail is null (e.g., data is still loading or API call failed)
        Text(
            text = "User detail data is unavailable",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun LoadingIndicator(isLoading: Boolean) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
    }
}


@Composable
fun RepositoryItem(repo: UserRepositoryResult) {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Text(
                text = repo.name, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            if (!repo.description.isNullOrEmpty())
                Text(
                    text = repo.description ?: "",
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(top = 4.dp)
                )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(R.drawable.icon_star_rate),
                    contentDescription = "Star Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = repo.starCount.toString(),
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painterResource(R.drawable.icon_visibility),
                    contentDescription = "Watcher Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = repo.watchCount.toString(),
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}