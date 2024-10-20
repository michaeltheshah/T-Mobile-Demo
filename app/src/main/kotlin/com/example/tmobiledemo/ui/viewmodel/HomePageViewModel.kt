package com.example.tmobiledemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmobiledemo.data.model.homepage.HomePageUIResponse
import com.example.tmobiledemo.data.repository.search.HomePageRepository
import com.example.tmobiledemo.util.state.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val repository: HomePageRepository
): ViewModel() {
    data class State(
        val isLoading: Boolean = false,
        val homePageResponse: HomePageUIResponse? = null,
        val error: Exception? = null,
    )

    private var _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state = _state.asStateFlow()

    fun fetchHomePage() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            when (val result = repository.getHomePage()) {
                is NetworkResult.Ok -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        homePageResponse = result.value,
                        error = null,
                    )
                }
                is NetworkResult.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        homePageResponse = null,
                        error = result.exception,
                    )
                }
            }
        }
    }
}