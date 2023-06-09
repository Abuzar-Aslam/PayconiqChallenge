package com.example.payconiqchallenge.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.payconiqchallenge.presentation.detail.UserDetailUI
import com.example.payconiqchallenge.presentation.detail.UserDetailViewModel
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
    val searchViewModel: UserSearchViewModel = koinContext.get()
    val detailViewModel: UserDetailViewModel = koinContext.get()

    NavHost(navController = navHostController, startDestination = NavPath.UserSearch.route) {


        composable(NavPath.UserSearch.route) {
            UserSearchUI(
                navHostController = navHostController,
                userSearchViewModel = searchViewModel
            )
        }


        composable(
            "${NavPath.UserDetail.route}?name={name}", arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                })
        ) {
            UserDetailUI(
                navHostController = navHostController,
                userDetailViewModel = detailViewModel
            )
        }
    }

}