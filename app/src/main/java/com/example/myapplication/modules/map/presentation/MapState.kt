package com.example.myapplication.modules.map.presentation

import com.example.myapplication.modules.map.data.models.Stop

data class MapState(
    val stops: List<Stop> = listOf(),
    val isLoading: Boolean = false,
    val errorCode: Int? = null,
    val exception: Throwable? = null,
    val errorMessage: String? = null
)
