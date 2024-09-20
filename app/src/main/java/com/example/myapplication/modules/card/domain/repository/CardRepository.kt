package com.example.myapplication.modules.card.domain.repository

import com.example.myapplication.core.backend.BackendResult
import com.example.myapplication.modules.card.domain.model.Card
import com.example.myapplication.modules.card.domain.model.CardBalance
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun validateCard(cardNumber: String): Flow<BackendResult<String>>
    fun getCardInformation(cardNumber: String): Flow<BackendResult<Card>>
    fun getCardBalance(cardNumber: String): Flow<BackendResult<CardBalance>>
}