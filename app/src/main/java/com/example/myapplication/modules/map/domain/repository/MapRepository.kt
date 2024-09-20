package com.example.myapplication.modules.map.domain.repository

import com.example.myapplication.core.backend.BackendResult
import com.example.myapplication.modules.map.data.models.Stop
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    fun getStopsNearLocation(
        latitude: Double,
        longitude: Double,
        radius: Int = 1000
    ): Flow<BackendResult<List<Stop>>>
}