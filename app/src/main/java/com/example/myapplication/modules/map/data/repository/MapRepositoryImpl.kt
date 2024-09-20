package com.example.myapplication.modules.map.data.repository

import com.example.myapplication.core.backend.BackendResult
import com.example.myapplication.modules.map.data.api.OpenTripPlannerService
import com.example.myapplication.modules.map.data.models.Stop
import com.example.myapplication.modules.map.domain.repository.MapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val api: OpenTripPlannerService
) : MapRepository {

    override fun getStopsNearLocation(
        latitude: Double,
        longitude: Double,
        radius: Int
    ): Flow<BackendResult<List<Stop>>>{
        return api.getStopsNearLocation(latitude, longitude, radius)
    }

}