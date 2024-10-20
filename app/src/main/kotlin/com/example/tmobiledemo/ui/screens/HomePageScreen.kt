package com.example.tmobiledemo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tmobiledemo.data.model.homepage.Card
import com.example.tmobiledemo.data.model.homepage.CardType
import com.example.tmobiledemo.ui.screens.components.ImageTitleDescriptionComponent
import com.example.tmobiledemo.ui.screens.components.TextComponent
import com.example.tmobiledemo.ui.screens.components.TitleDescriptionComponent
import com.example.tmobiledemo.ui.viewmodel.HomePageViewModel

@Composable
fun HomePageScreen(
    viewModel: HomePageViewModel,
    contentPadding: PaddingValues,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchHomePage()
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        Modifier
            .fillMaxSize()
            .padding(contentPadding)) {

        if (state.isLoading) {
            LinearProgressIndicator(Modifier.fillMaxWidth())
        }

        val cards = state.homePageResponse?.page?.cards.orEmpty()
        LazyColumn {
            items(cards) { card ->
                when (card.cardType) {
                    CardType.TEXT -> TextComponent(card.card)
                    CardType.TITLE_DESC -> TitleDescriptionComponent(card.card)
                    CardType.IMAGE_TITLE_DESC -> ImageTitleDescriptionComponent(card.card)
                }
            }
        }
    }
}