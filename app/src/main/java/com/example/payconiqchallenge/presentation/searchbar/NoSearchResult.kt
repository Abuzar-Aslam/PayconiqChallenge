package com.example.payconiqchallenge.presentation.searchbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.payconiqchallenge.R

/**
 * Composable function for displaying a message when there are no search results.
 *
 * @param searchText The current search text entered by the user.
 */
@Composable
fun NoSearchResults(errorMessage: String) {

    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.no_search_icon_description),
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}