package com.example.myapplication.modules.card.domain.usecase

import com.example.myapplication.core.database.entities.CardEntity
import com.example.myapplication.modules.card.domain.repository.LocalCardRepository
import javax.inject.Inject

class AddCardUseCase @Inject constructor(private val repository: LocalCardRepository) {
    suspend operator fun invoke(cardEntity: CardEntity) { repository.saveCard(cardEntity) }
}