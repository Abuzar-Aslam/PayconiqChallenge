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
import com.example.payconiqchallenge.presentation.userdetail.UserDetailUI
import com.example.payconiqchallenge.presentation.userdetail.UserDetailViewModel
import com.example.payconiqchallenge.presentation.usersearch.UserSearchScreen
import com.example.payconiqchallenge.presentation.usersearch.UserSearchViewModel
import org.koin.java.KoinJavaComponent.getKoin

/**
 * Enum class representing the navigation paths/routes for the app.
 *
 * @param route The string representation of the route.
 */
enum class NavPath(
    val route: String,
) {
    UserSearch(route = "user_search"),
    UserDetail(route = "user_detail")
}

/**
 * Composable function representing the app's navigation host.
 *
 * @param navHostController The NavHostController responsible for handling navigation.
 * @param scaffoldState The ScaffoldState used for displaying the app's UI elements.
 */
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AppNavHost(navHostController: NavHostController, scaffoldState: ScaffoldState) {

    val koinContext = getKoin()
    val searchViewModel: UserSearchViewModel = koinContext.get()
    val detailViewModel: UserDetailViewModel = koinContext.get()

    NavHost(navController = navHostController, startDestination = NavPath.UserSearch.route) {


        composable(NavPath.UserSearch.route) {
            UserSearchScreen(
                navHostController = navHostController,
                userSearchViewModel = searchViewModel
            )
        }


        composable(
            "${NavPath.UserDetail.route}?name={name}", arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val selectedUserName = backStackEntry.arguments?.getString("name") ?: ""

            UserDetailUI(
                navHostController = navHostController,
                userDetailViewModel = detailViewModel,
                selectedUserName = selectedUserName
            )
        }
    }

}