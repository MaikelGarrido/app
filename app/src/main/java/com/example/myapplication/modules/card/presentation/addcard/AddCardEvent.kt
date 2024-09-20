package com.example.myapplication.modules.card.presentation.addcard

sealed interface AddCardEvent {
    data class OnChanged(val cardNumber: String) : AddCardEvent
    data object ValidateCard : AddCardEvent
    data class GetInformation(val cardNumber: String) : AddCardEvent
    data object OnClear : AddCardEvent
}