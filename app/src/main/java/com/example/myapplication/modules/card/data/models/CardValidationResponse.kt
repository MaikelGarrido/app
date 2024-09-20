package com.example.myapplication.modules.card.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CardValidationResponse(
    val card: String,
    val isValid: Boolean,
    val status: String,
    val statusCode: Int
)