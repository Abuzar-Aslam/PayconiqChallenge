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


@Composable
fun NoSearchResults(searchText: String) {

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
            text = stringResource(if (searchText.isEmpty()) R.string.no_search_message else R.string.no_search_result_message),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}