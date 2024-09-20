package com.example.myapplication.modules.card.presentation.addcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.backend.BackendResult
import com.example.myapplication.modules.card.domain.model.Card
import com.example.myapplication.modules.card.domain.model.Card.Companion.toEntity
import com.example.myapplication.modules.card.domain.usecase.CardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val useCases: CardUseCases,
): ViewModel() {

    var state = MutableStateFlow(AddCardState())
        private set

    fun onEvent(event: AddCardEvent) {
        viewModelScope.launch {
            when(event) {
                is AddCardEvent.OnChanged -> { onChanged(event.cardNumber) }
                is AddCardEvent.ValidateCard -> { onValidateCard() }
                is AddCardEvent.GetInformation -> { onGetInformation(event.cardNumber) }
                AddCardEvent.OnClear -> { onClear() }

            }
        }
    }

    private fun onChanged(cardNumber: String) {
        viewModelScope.launch {
            state.update {
                it.copy(
                    isEnabled = cardNumber.isNotBlank(),
                    cardNumber = cardNumber
                )
            }
        }
    }

    private fun onValidateCard() {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            useCases.validateCardUseCase.invoke(state.value.cardNumber.trim())
                .distinctUntilChanged()
                .collect { result ->
                    when(result) {
                        is BackendResult.Error -> { onError(result.message, result.code) }
                        is BackendResult.Exception -> { onException(result.e) }
                        is BackendResult.Success -> {
                            if (!result.data.isNullOrBlank()) {
                                onEvent(AddCardEvent.GetInformation(result.data))
                            }
                        }
                    }
                }
        }
    }

    private fun onGetInformation(cardNumber: String) {
        viewModelScope.launch {
            useCases.getInfoCardUseCase.invoke(cardNumber.trim())
                .distinctUntilChanged()
                .collect { result ->
                    when(result) {
                        is BackendResult.Error -> { onError(result.message, result.code) }
                        is BackendResult.Exception -> { onException(result.e) }
                        is BackendResult.Success -> { onSuccess(result.data)}
                    }
                }
        }
    }

    private fun onClear() {
        viewModelScope.launch {
            state.update {
                AddCardState()
            }
        }
    }

    private fun onSuccess(data: Card?) {
        viewModelScope.launch {
            if (data != null) {
                useCases.addCardUseCase.invoke(data.toEntity())
                state.update {
                    it.copy(
                        isLoading = false,
                        isValid = true,
                    )
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
}