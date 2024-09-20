package com.example.myapplication.modules.card.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.backend.BackendResult
import com.example.myapplication.modules.card.domain.usecase.CardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDetailsViewModel @Inject constructor(
    private val usesCases: CardUseCases
): ViewModel() {

    var state = MutableStateFlow(CardDetailsState())
        private set

    fun onEvent(event: CardDetailsEvent) {
        viewModelScope.launch {
            when(event) {
                is CardDetailsEvent.GetBalance -> { onGetBalance(event.cardNumber) }
                CardDetailsEvent.OnClear -> { onClear() }
            }
        }
    }

    private fun onGetBalance(cardNumber: String) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            usesCases.getCardBalanceUseCase.invoke(cardNumber)
                .distinctUntilChanged()
                .collectLatest { result ->
                    when(result) {
                        is BackendResult.Error -> { onError(result.message, result.code) }
                        is BackendResult.Exception -> { onException(result.e) }
                        is BackendResult.Success -> {
                            if (result.data != null) {
                                state.update {
                                    it.copy(
                                        isLoading = false,
                                        cardBalance = result.data
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }

    private fun onError(message: String?, code: Int? = 0) {
        viewModelScope.launch {
            state.update {
                it.copy(
                    isLoading = false,
                    errorMessage = message ?: "Hubo un error",
                    errorCode = code
                )
            }
        }
    }

    private fun onException(e: Throwable) {
        viewModelScope.launch {
            state.update {
                it.copy(
                    isLoading = false,
                    exception = e,
                )
            }
        }
    }

    private fun onClear() {
        viewModelScope.launch {
            state.update { CardDetailsState() }
        }
    }

}