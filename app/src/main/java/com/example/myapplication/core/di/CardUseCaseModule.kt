package com.example.myapplication.core.di

import com.example.myapplication.modules.card.domain.repository.CardRepository
import com.example.myapplication.modules.card.domain.repository.LocalCardRepository
import com.example.myapplication.modules.card.domain.usecase.AddCardUseCase
import com.example.myapplication.modules.card.domain.usecase.CardUseCases
import com.example.myapplication.modules.card.domain.usecase.DeleteCardUseCase
import com.example.myapplication.modules.card.domain.usecase.GetCardBalanceUseCase
import com.example.myapplication.modules.card.domain.usecase.GetCardsUseCase
import com.example.myapplication.modules.card.domain.usecase.GetInfoCardUseCase
import com.example.myapplication.modules.card.domain.usecase.ValidateCardUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CardUseCaseModule {

    @Provides
    @Singleton
    fun provideCardUseCase(
        localCardRepository: LocalCardRepository,
        cardRepository: CardRepository
    ): CardUseCases {
        return CardUseCases(
            validateCardUseCase = ValidateCardUseCase(cardRepository),
            getInfoCardUseCase = GetInfoCardUseCase(cardRepository),
            getCardBalanceUseCase = GetCardBalanceUseCase(cardRepository),
            addCardUseCase = AddCardUseCase(localCardRepository),
            deleteCardUseCase = DeleteCardUseCase(localCardRepository),
            getCardsUseCase = GetCardsUseCase(localCardRepository)
        )
    }

}
