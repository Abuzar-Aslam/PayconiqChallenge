package com.example.payconiqchallenge.presentation.searchbar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.payconiqchallenge.R

/**
 * Composable function for displaying a search bar UI.
 *
 * @param searchText The current search text entered by the user.
 * @param placeholderText The placeholder text to display in the search bar when it is empty.
 * @param onSearchTextChanged The callback function to invoke when the search text changes.
 * @param onClearClick The callback function to invoke when the clear button is clicked.
 * @param matchesFound Flag indicating if any search results are found.
 * @param results The composable function to display search results.
 */
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SearchBarUI(
    searchText: String,
    placeholderText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    matchesFound: Boolean,
    errorMessage: String = stringResource(id = R.string.no_search_message),
    results: @Composable () -> Unit = {},
) {

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            SearchBar(
                searchText,
                placeholderText,
                onSearchTextChanged,
                onClearClick
            )

            if (matchesFound) {
                Text("Results", modifier = Modifier.padding(8.dp), fontWeight = FontWeight.Bold)
                results()
            } else {
                NoSearchResults(errorMessage)
            }
        }
    }
}