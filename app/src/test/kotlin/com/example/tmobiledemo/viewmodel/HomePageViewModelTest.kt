package com.example.tmobiledemo.viewmodel

import app.cash.turbine.test
import com.example.tmobiledemo.data.model.homepage.HomePageUIResponse
import com.example.tmobiledemo.data.repository.search.HomePageService
import com.example.tmobiledemo.repository.FakeHomePageRepository
import com.example.tmobiledemo.ui.viewmodel.HomePageViewModel
import com.example.tmobiledemo.util.state.UIState
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomePageViewModelTest {
    private val homePageService = mockk<HomePageService>()
    private lateinit var viewModel: HomePageViewModel
    private lateinit var repository: FakeHomePageRepository
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    private val testScope = TestScope(testDispatcher)
    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        isLenient = true
        explicitNulls = false
        coerceInputValues = true
    }

    private var homePageUIResponse: HomePageUIResponse? = null

    @OptIn(ExperimentalSerializationApi::class, ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        repository = FakeHomePageRepository(homePageService)

        homePageUIResponse = try {
            val stream = javaClass.getResourceAsStream("/home_page_response.json") ?: return
            json.decodeFromStream<HomePageUIResponse>(stream)
        } catch (e: Exception) {
            null
        }

        viewModel = HomePageViewModel(repository = repository)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `initial state is correct`() {
        assertEquals(UIState.Idle(), viewModel.state)
    }

    @Test
    fun `stateFlow should emit null when input is blank`() = testScope.runTest {
        viewModel.state.test {
            assertEquals(HomePageViewModel.State(), awaitItem())

            viewModel.fetchHomePage()

            assertEquals(HomePageViewModel.State(isLoading = true), awaitItem())
            assertEquals(HomePageViewModel.State(homePageResponse = homePageUIResponse), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }
}
