package com.example.myapplication.modules.card.data.repository

import com.example.myapplication.core.backend.BackendResult
import com.example.myapplication.modules.card.data.api.TullaveApiService
import com.example.myapplication.modules.card.domain.model.Card
import com.example.myapplication.modules.card.domain.model.CardBalance
import com.example.myapplication.modules.card.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val api: TullaveApiService
) : CardRepository {

    override fun validateCard(cardNumber: String): Flow<BackendResult<String>> {
        return api.validateCard(cardNumber)
    }

    override fun getCardInformation(cardNumber: String): Flow<BackendResult<Card>> {
        return api.getCardInformation(cardNumber)
    }

    override fun getCardBalance(cardNumber: String): Flow<BackendResult<CardBalance>> {
        return api.getCardBalance(cardNumber)
    }

}