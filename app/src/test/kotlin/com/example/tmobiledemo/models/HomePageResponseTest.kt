package com.example.tmobiledemo.models

import com.example.tmobiledemo.data.model.homepage.HomePageUIResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomePageResponseTest {
    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        isLenient = true
        explicitNulls = false
        coerceInputValues = true
    }
    private var homePageUIResponse: HomePageUIResponse? = null

    @OptIn(ExperimentalSerializationApi::class)
    @Before
    fun setUp() {
        homePageUIResponse = try {
            val stream = javaClass.getResourceAsStream("/home_page_response.json") ?: return
            json.decodeFromStream<HomePageUIResponse>(stream)
        } catch (e: Exception) {
            null
        }
    }

    @Test
    fun `test should have search results`() {
        assert(homePageUIResponse?.page?.cards?.size == 6)
    }
}