package com.example.myapplication.modules.map.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Stop(
    val id: String,
    val code: String,
    val name: String,
    val lat: Double,
    val lon: Double,
    val dist: Double
)