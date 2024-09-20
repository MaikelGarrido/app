package com.example.myapplication.modules.card.data.models

import com.example.myapplication.modules.card.domain.model.CardBalance
import kotlinx.serialization.Serializable

@Serializable
data class CardBalanceResponse(
    val card: String,
    val balance: Int,
    val balanceDate: String,
    val virtualBalance: Int,
    val virtualBalanceDate: String
) {
    companion object {
        fun CardBalanceResponse.toCardBalance(): CardBalance {
            return CardBalance(
                card = this.card,
                balance = this.balance,
                balanceDate = this.balanceDate,
                virtualBalance = this.virtualBalance,
                virtualBalanceDate = this.virtualBalanceDate
            )
        }
    }
}
