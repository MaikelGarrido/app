package com.example.myapplication.modules.map.presentation

sealed interface MapEvent {
    data class LoadStops(
        val latitude: Double = 4.5981,
        val longitude: Double = -74.0760,
        val radius: Int = 1000
    ) : MapEvent
}