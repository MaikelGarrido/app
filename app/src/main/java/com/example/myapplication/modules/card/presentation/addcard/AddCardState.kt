package com.example.myapplication.modules.card.presentation.addcard

data class AddCardState(
    val cardNumber: String = "",
    val isValid: Boolean? = null,
    val isEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val errorCode: Int? = null,
    val exception: Throwable? = null,
    val errorMessage: String? = null
)
