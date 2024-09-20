package com.example.myapplication.modules.card.presentation.cardlist

sealed interface CardListEvent {
    data class DeleteCard(val cardNumber : String) : CardListEvent
}