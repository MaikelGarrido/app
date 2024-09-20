package com.example.myapplication.modules.card.domain.usecase

data class CardUseCases(
    val validateCardUseCase: ValidateCardUseCase,
    val getInfoCardUseCase: GetInfoCardUseCase,
    val getCardBalanceUseCase: GetCardBalanceUseCase,
    val addCardUseCase: AddCardUseCase,
    val deleteCardUseCase: DeleteCardUseCase,
    val getCardsUseCase: GetCardsUseCase,
)
