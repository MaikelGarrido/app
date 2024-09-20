package com.example.myapplication.modules.card.domain.model

import com.example.myapplication.core.database.entities.CardEntity
import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val cardNumber: String,
    val profileCode: String,
    val profile: String,
    val profileES: String,
    val bankCode: String,
    val bankName: String,
    val userName: String,
    val userLastName: String,
) {
    companion object {
        fun Card.getFullName(): String {
            return "${this.userName} ${this.userLastName}".trim()
        }

        fun Card.toEntity(): CardEntity {
            return CardEntity(
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
    }
}