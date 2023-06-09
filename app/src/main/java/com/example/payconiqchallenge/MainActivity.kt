package com.example.payconiqchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.example.payconiqchallenge.navigation.AppNavHost
import com.example.payconiqchallenge.ui.theme.ComposeSearchViewSampleTheme

/**
 * The main activity of the application that sets up the Compose UI and navigation.
 */
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is created. Sets up the Compose UI and navigation.
     */
    @OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val scaffoldState = rememberScaffoldState()
            val navController = rememberNavController()

            ComposeSearchViewSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppNavHost(navController, scaffoldState)
                }
            }
        }
    }
}