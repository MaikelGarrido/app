package com.example.myapplication.modules.card.presentation.details

sealed interface CardDetailsEvent {
    data class GetBalance(val cardNumber: String) : CardDetailsEvent
    data object OnClear : CardDetailsEvent
}