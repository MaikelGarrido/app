package com.example.myapplication.modules.card.domain.repository

import com.example.myapplication.core.database.entities.CardEntity
import kotlinx.coroutines.flow.Flow

interface LocalCardRepository {
    suspend fun saveCard(cardEntity: CardEntity)
    fun getCards(): Flow<List<CardEntity>>
    suspend fun removeCard(cardNumber: String)
}