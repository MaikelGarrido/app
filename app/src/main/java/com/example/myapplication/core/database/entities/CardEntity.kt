package com.example.myapplication.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.modules.card.domain.model.Card

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey val cardNumber: String,
    val profileCode: String,
    val profile: String,
    val profileES: String,
    val bankCode: String,
    val bankName: String,
    val userName: String,
    val userLastName: String,
) {
    companion object {
        private fun CardEntity.toCard(): Card {
            return Card(
                cardNumber = this.cardNumber,
                profileCode = this.profileCode,
                profile = this.profile,
                profileES = this.profileES,
                bankCode = this.bankCode,
                bankName = this.bankName,
                userName = this.userName,
                userLastName = this.userLastName
            )
        }

        fun List<CardEntity>.toCardList(): List<Card> {
            return this.map { it.toCard() }
        }
    }
}
