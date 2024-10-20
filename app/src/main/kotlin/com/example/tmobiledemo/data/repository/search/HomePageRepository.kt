package com.example.tmobiledemo.data.repository.search

import com.example.tmobiledemo.data.model.homepage.HomePageUIResponse
import com.example.tmobiledemo.util.extensions.awaitResult
import com.example.tmobiledemo.util.state.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class HomePageRepository @Inject constructor(private val service: HomePageService) {
    open suspend fun getHomePage(): NetworkResult<HomePageUIResponse> {
        return withContext(Dispatchers.IO) {
            service.getHomePageUI().awaitResult()
        }
    }
}