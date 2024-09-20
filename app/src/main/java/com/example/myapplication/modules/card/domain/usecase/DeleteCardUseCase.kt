package com.example.myapplication.modules.card.domain.usecase

import com.example.myapplication.modules.card.domain.repository.LocalCardRepository
import javax.inject.Inject

class DeleteCardUseCase @Inject constructor(private val repository: LocalCardRepository) {
    suspend operator fun invoke(cardNumber: String) {
        repository.removeCard(cardNumber)
    }
}