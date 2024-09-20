package com.example.myapplication.modules.map.domain.usecase

import com.example.myapplication.core.backend.BackendResult
import com.example.myapplication.modules.map.data.models.Stop
import com.example.myapplication.modules.map.domain.repository.MapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MapLocationUseCase @Inject constructor(private val repo: MapRepository) {

    operator fun invoke(
        latitude: Double,
        longitude: Double,
        radius: Int = 1000
    ): Flow<BackendResult<List<Stop>>> {
        return repo.getStopsNearLocation(latitude, longitude, radius)
    }

}