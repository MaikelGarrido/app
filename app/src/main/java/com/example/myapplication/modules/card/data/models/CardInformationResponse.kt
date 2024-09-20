package com.example.myapplication.modules.card.data.models

import com.example.myapplication.modules.card.domain.model.Card
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardInformationResponse(
    val cardNumber: String,
    val profileCode: String,
    val profile: String,
    @SerialName("profile_es")
    val profileES: String,
    val bankCode: String,
    val bankName: String,
    val userName: String,
    val userLastName: String,
) {
    companion object {
        fun CardInformationResponse.toCard(): Card {
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
    }
}
