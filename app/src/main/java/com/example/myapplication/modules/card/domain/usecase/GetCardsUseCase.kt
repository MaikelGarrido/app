package com.example.myapplication.modules.card.domain.usecase

import com.example.myapplication.core.database.entities.CardEntity.Companion.toCardList
import com.example.myapplication.modules.card.domain.model.Card
import com.example.myapplication.modules.card.domain.repository.LocalCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(private val repository: LocalCardRepository) {
    operator fun invoke(): Flow<List<Card>> {
        return repository.getCards().map { it.toCardList() }
    }
}