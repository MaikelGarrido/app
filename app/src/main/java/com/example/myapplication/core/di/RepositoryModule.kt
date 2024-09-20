package com.example.myapplication.core.di

import com.example.myapplication.core.database.dao.CardDAO
import com.example.myapplication.modules.card.data.api.TullaveApiService
import com.example.myapplication.modules.card.data.repository.CardRepositoryImpl
import com.example.myapplication.modules.card.data.repository.LocalCardRepositoryImpl
import com.example.myapplication.modules.card.domain.repository.CardRepository
import com.example.myapplication.modules.card.domain.repository.LocalCardRepository
import com.example.myapplication.modules.map.data.api.OpenTripPlannerService
import com.example.myapplication.modules.map.data.repository.MapRepositoryImpl
import com.example.myapplication.modules.map.domain.repository.MapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTullaveApiService(httpClient: HttpClient, json: Json): TullaveApiService {
        return TullaveApiService(httpClient, json)
    }

    @Provides
    @Singleton
    fun provideOpenTripPlannerService(httpClient: HttpClient, json: Json): OpenTripPlannerService {
        return OpenTripPlannerService(httpClient, json)
    }

    @Provides
    @Singleton
    fun provideMapRepository(api: OpenTripPlannerService): MapRepository {
        return MapRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCardRepository(api: TullaveApiService) : CardRepository {
        return CardRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(dao: CardDAO) : LocalCardRepository {
        return LocalCardRepositoryImpl(dao)
    }

}