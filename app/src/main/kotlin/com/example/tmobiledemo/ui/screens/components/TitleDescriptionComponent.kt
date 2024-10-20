package com.example.tmobiledemo.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmobiledemo.data.model.homepage.Card

@Composable
fun TitleDescriptionComponent(card: Card) {
    Column(modifier = Modifier.padding(8.dp)) {
        val title = card.title
        if (title != null) {
            Text(text = title.value.orEmpty(), fontSize = title.fontSize.sp, color = title.fontColor)
        }

        val description = card.description
        if (description != null) {
            Text(text = description.value.orEmpty(), fontSize = description.fontSize.sp, color = description.fontColor)
        }
    }
}