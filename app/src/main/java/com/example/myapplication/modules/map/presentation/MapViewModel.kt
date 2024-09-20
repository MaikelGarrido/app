package com.example.myapplication.modules.map.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.backend.BackendResult
import com.example.myapplication.modules.map.data.models.Stop
import com.example.myapplication.modules.map.domain.usecase.MapUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val useCases: MapUseCases
): ViewModel() {

    var state = MutableStateFlow(MapState())
        private set

    fun onEvent(event: MapEvent) {
        viewModelScope.launch {
            when(event) {
                is MapEvent.LoadStops -> { onLoadStops(event.latitude, event.longitude, event.radius) }
            }
        }
    }

    private fun onLoadStops(
        latitude: Double = 4.5981,
        longitude: Double = -74.0760,
        radius: Int = 1000
    ) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            useCases.mapLocationUseCase.invoke(
                latitude = latitude,
                longitude = longitude,
                radius = radius
            )
                .distinctUntilChanged()
                .collectLatest { result ->
                    when(result) {
                        is BackendResult.Error -> { onError(result.message, result.code) }
                        is BackendResult.Exception -> { onException(result.e) }
                        is BackendResult.Success -> { onSuccess(result.data) }
                    }
            }
        }
    }

    private fun onSuccess(data: List<Stop>?) {
        viewModelScope.launch {
            state.update {
                it.copy(
                    isLoading = false,
                    stops = data!!
                )
            }
        }
    }

    private fun onError(message: String?, code: Int? = 0) {
        viewModelScope.launch {
            state.update {
                it.copy(
                    isLoading = false,
                    errorMessage = message ?: "Hubo un error",
                    errorCode = code
                )
            }
        }
    }

    private fun onException(e: Throwable) {
        viewModelScope.launch {
            state.update {
                it.copy(
                    isLoading = false,
                    exception = e,
                )
            }
        }
    }

}