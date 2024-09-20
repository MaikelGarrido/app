package com.example.myapplication.modules.card.presentation.details

import com.example.myapplication.modules.card.domain.model.CardBalance

data class CardDetailsState(
    val cardBalance: CardBalance? = null,
    val isLoading: Boolean = false,
    val errorCode: Int? = null,
    val exception: Throwable? = null,
    val errorMessage: String? = null
)
