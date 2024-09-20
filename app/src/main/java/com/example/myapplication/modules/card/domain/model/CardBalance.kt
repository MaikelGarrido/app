package com.example.myapplication.modules.card.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CardBalance(
    val card: String,
    val balance: Int,
    val balanceDate: String,
    val virtualBalance: Int,
    val virtualBalanceDate: String
)
