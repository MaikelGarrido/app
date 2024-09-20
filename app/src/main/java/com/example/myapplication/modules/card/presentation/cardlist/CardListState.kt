package com.example.myapplication.modules.card.presentation.cardlist

import com.example.myapplication.modules.card.domain.model.Card

data class CardListState(
    val cards: List<Card> = listOf(),
    val isLoading: Boolean = false,
    val errorCode: Int? = null,
    val exception: Throwable? = null,
    val errorMessage: String? = null
)
