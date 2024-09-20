package com.example.myapplication.modules.card.presentation.cardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modules.card.domain.usecase.CardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val useCases: CardUseCases
) : ViewModel() {

    var state = MutableStateFlow(CardListState())
        private set

    val cards = useCases.getCardsUseCase.invoke()
        .catch { onException(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    init {
        viewModelScope.launch {
            useCases.getCardsUseCase.invoke()
                .catch { onException(it) }
                .collect { cards -> state.update { it.copy(cards = cards) } }
        }
    }

    fun onEvent(event: CardListEvent) {
        viewModelScope.launch {
            when(event) {
                is CardListEvent.DeleteCard -> { onDeleteCard(event.cardNumber) }
            }
        }
    }

    private fun onDeleteCard(cardNumber: String) {
        viewModelScope.launch {
            useCases.deleteCardUseCase.invoke(cardNumber)
        }
    }

    private fun onError(message: String?, code: Int? = 0) {
        viewModelScope.launch {
            state.update {
                it.copy(
                    isLoading = false,
                    errorMessage = message ?: "Hubo un error",
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
            state.update { CardListState() }
        }
    }


}