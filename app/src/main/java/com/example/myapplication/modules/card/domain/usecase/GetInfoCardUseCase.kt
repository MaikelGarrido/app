package com.example.myapplication.modules.card.domain.usecase

import com.example.myapplication.core.backend.BackendResult
import com.example.myapplication.modules.card.domain.model.Card
import com.example.myapplication.modules.card.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInfoCardUseCase @Inject constructor(private val repository: CardRepository) {
    operator fun invoke(cardNumber: String): Flow<BackendResult<Card>> {
        return repository.getCardInformation(cardNumber)
    }
}