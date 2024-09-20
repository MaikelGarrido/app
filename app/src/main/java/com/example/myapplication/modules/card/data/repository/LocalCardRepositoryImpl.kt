package com.example.myapplication.modules.card.data.repository

import com.example.myapplication.core.database.dao.CardDAO
import com.example.myapplication.core.database.entities.CardEntity
import com.example.myapplication.modules.card.domain.repository.LocalCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalCardRepositoryImpl @Inject constructor(
    private val dao: CardDAO
): LocalCardRepository {

    override suspend fun saveCard(cardEntity: CardEntity) {
        dao.insertCard(cardEntity)
    }

    override fun getCards(): Flow<List<CardEntity>> {
        return dao.getAllCards()
    }

    override suspend fun removeCard(cardNumber: String) {
        return dao.deleteCard(cardNumber)
    }
}