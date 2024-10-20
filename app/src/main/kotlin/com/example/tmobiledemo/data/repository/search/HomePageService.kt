package com.example.tmobiledemo.data.repository.search

import com.example.tmobiledemo.data.model.homepage.HomePageUIResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomePageService {
    @GET("/test/home")
    suspend fun getHomePageUI(): Response<HomePageUIResponse>
}