package com.example.payconiqchallenge.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.payconiqchallenge.presentation.search.UserSearchUI
import com.example.payconiqchallenge.presentation.search.UserSearchViewModel
import org.koin.java.KoinJavaComponent.getKoin

enum class NavPath(
    val route: String,
) {
    UserSearch(route = "user_search"),
    UserDetail(route = "user_detail")
}


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AppNavHost(navHostController: NavHostController, scaffoldState: ScaffoldState) {

    val koinContext = getKoin()
    val viewModel: UserSearchViewModel = koinContext.get()

    NavHost(navController = navHostController, startDestination = NavPath.UserSearch.route) {


        composable(NavPath.UserSearch.route) {
            UserSearchUI(
                navHostController = navHostController,
                userSearchViewModel = viewModel
            )
        }


        composable(NavPath.UserDetail.route){

        }
    }

}