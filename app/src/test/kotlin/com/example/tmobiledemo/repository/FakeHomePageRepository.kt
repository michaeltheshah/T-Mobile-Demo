package com.example.tmobiledemo.repository

import com.example.tmobiledemo.data.model.homepage.Attributes
import com.example.tmobiledemo.data.model.homepage.Card
import com.example.tmobiledemo.data.model.homepage.CardType
import com.example.tmobiledemo.data.model.homepage.Font
import com.example.tmobiledemo.data.model.homepage.HomePageUIResponse
import com.example.tmobiledemo.data.model.homepage.Page
import com.example.tmobiledemo.data.model.homepage.PageCard
import com.example.tmobiledemo.data.model.homepage.TitleDescription
import com.example.tmobiledemo.data.repository.search.HomePageRepository
import com.example.tmobiledemo.data.repository.search.HomePageService
import com.example.tmobiledemo.util.state.NetworkResult
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class FakeHomePageRepository @Inject constructor(service: HomePageService) : HomePageRepository(service) {
    var shouldReturnError = false

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        isLenient = true
        explicitNulls = false
        coerceInputValues = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val fakeResponse: HomePageUIResponse
        get() {
            val stream = javaClass.getResourceAsStream("/home_page_response.json") ?: throw IllegalStateException()
            return json.decodeFromStream<HomePageUIResponse>(stream)
        }

    override suspend fun getHomePage(): NetworkResult<HomePageUIResponse> {
        return if (shouldReturnError) {
            NetworkResult.Error(IllegalStateException(""))
        } else {
            NetworkResult.Ok(fakeResponse)
        }
    }
}
