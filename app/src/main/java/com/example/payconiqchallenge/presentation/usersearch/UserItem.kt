package com.example.payconiqchallenge.presentation.usersearch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.payconiqchallenge.domain.model.UserModel

@Composable
fun UserItem(user: UserModel, onClick: () -> Unit) {

    Card(modifier = Modifier
        .padding(8.dp)
        .clickable { onClick() }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = "User Image",
                modifier = Modifier
                    .size(96.dp)
                    .clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = user.login,
                style = TextStyle(fontWeight = FontWeight.Bold),
            )
        }


    }

}