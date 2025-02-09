package com.example.tmobiledemo.di

import com.example.tmobiledemo.data.repository.search.HomePageService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    val json = Json {
        ignoreUnknownKeys = true
        allowStructuredMapKeys = true
        encodeDefaults = false
        explicitNulls = false
        isLenient = true
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)

            /* If an API key or bearer token was required for the request, this is where it would be injected
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val request = originalRequest.newBuilder()

                request.addHeader("Authorization", "Bearer $token")

                chain.proceed(request.build())
            }
             */
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://private-8ce77c-tmobiletest.apiary-mock.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun providesTMobileService(retrofit: Retrofit): HomePageService {
        return retrofit.create(HomePageService::class.java)
    }
}