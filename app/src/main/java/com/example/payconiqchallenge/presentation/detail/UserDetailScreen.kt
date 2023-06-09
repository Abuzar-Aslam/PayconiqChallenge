package com.example.payconiqchallenge.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.payconiqchallenge.presentation.model.UserDetailState
import com.example.payconiqchallenge.presentation.model.UserSearchState
import com.example.payconiqchallenge.presentation.search.UserSearchViewModel
import com.example.payconiqchallenge.utils.rememberFlowWithLifecycle

@Composable
fun UserDetailUI(userDetailViewModel: UserDetailViewModel, navHostController: NavHostController) {


    val userDetailState by rememberFlowWithLifecycle(userDetailViewModel.userDetailState)
        .collectAsState(initial = UserDetailState.Empty)


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "User Detail")
                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = it)
            //.verticalScroll(rememberScrollState())
        ) {
//            Image(
//                painter = rememberImagePainter(user.avatarUrl),
//                contentDescription = "User Avatar",
//                modifier = Modifier
//                    .size(120.dp)
//                    .clip(CircleShape)
//                    .align(Alignment.CenterHorizontally)
//            )
            Text(
                text = userDetailState.userDetail!!.name,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Followers: ${userDetailState.userDetail!!.followers}",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Following: ${userDetailState.userDetail!!.following}",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Repositories",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyColumn {
                items(repos) { repo ->
                    RepositoryItem(repo)
                }
            }
        }
    }
}


@Composable
fun RepositoryItem(repo: Repository) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clickable { /* Handle item click */ }
    ) {
        Text(
            text = repo.name,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = repo.description ?: "",
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier.padding(top = 4.dp)
        )
        Row(
            modifier = Modifier.padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Star,
                contentDescription = "Star Icon",
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = repo.stargazersCount.toString(),
                style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.Default.Visibility,
                contentDescription = "Watcher Icon",
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = repo.watchersCount.toString(),
                style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}