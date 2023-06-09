package com.example.payconiqchallenge.presentation.detail

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

//@Composable
//fun UserDetailScreen(
//    viewModel: UserDetailViewModel,
//    username: String
//) {
//    val state by viewModel.state.collectAsState()
//
//    when {
//        state.loading -> {
//            CircularProgressIndicator()
//        }
//        state.userDetail != null -> {
//            val userDetail = state.userDetail
//            // Display user details
//        }
//        state.error != null -> {
//            Text("Error: ${state.error}")
//        }
//    }
//}