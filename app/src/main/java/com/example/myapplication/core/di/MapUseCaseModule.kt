package com.example.myapplication.core.di

import com.example.myapplication.modules.map.domain.repository.MapRepository
import com.example.myapplication.modules.map.domain.usecase.MapLocationUseCase
import com.example.myapplication.modules.map.domain.usecase.MapUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapUseCaseModule {

    @Provides
    @Singleton
    fun provideMapUseCase(
        repository: MapRepository
    ): MapUseCases {
        return MapUseCases(
            mapLocationUseCase = MapLocationUseCase(repository)
        )
    }
}