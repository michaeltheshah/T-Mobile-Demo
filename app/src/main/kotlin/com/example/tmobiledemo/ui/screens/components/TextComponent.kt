package com.example.tmobiledemo.ui.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmobiledemo.data.model.homepage.Card
import com.example.tmobiledemo.util.extensions.color

@Composable
fun TextComponent(textCard: Card) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = textCard.value.orEmpty(),
        fontSize = textCard.attributes?.font?.size?.sp ?: 0.sp,
        color = textCard.attributes?.textColor?.color ?: Color.Black
    )
}