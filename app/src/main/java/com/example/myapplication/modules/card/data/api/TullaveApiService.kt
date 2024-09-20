package com.example.myapplication.modules.card.data.api

import com.example.myapplication.core.backend.BackendError
import com.example.myapplication.core.backend.BackendResult
import com.example.myapplication.core.backend.ServiceUnavailable
import com.example.myapplication.modules.card.data.models.CardBalanceResponse
import com.example.myapplication.modules.card.data.models.CardBalanceResponse.Companion.toCardBalance
import com.example.myapplication.modules.card.data.models.CardInformationResponse
import com.example.myapplication.modules.card.data.models.CardInformationResponse.Companion.toCard
import com.example.myapplication.modules.card.data.models.CardValidationResponse
import com.example.myapplication.modules.card.domain.model.Card
import com.example.myapplication.modules.card.domain.model.CardBalance
import com.example.myapplication.modules.card.domain.repository.CardRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import javax.inject.Inject

class TullaveApiService @Inject constructor(
    private val httpClient: HttpClient,
    private val json: Json
) : CardRepository {

    override fun validateCard(cardNumber: String): Flow<BackendResult<String>> {
        return flow {
            if (cardNumber.isEmpty()) {
                emit(
                    BackendResult.Error(
                        code = 0,
                        message = "El campo no puede estar vacío."
                    )
                )
            } else {
                val response: HttpResponse = httpClient.get("card/valid/$cardNumber")
                val responseBody = response.bodyAsText()
                if (response.status == HttpStatusCode.OK) {
                    val validationResponse = json.decodeFromString<CardValidationResponse>(responseBody)
                    if (validationResponse.isValid) {
                        emit(BackendResult.Success(data = cardNumber))
                    } else {
                        emit(
                            BackendResult.Error(
                                code = validationResponse.statusCode,
                                message = "Card is invalid"
                            )
                        )
                    }
                } else if (response.status == HttpStatusCode.ServiceUnavailable) {
                    val error = json.decodeFromString<ServiceUnavailable>(responseBody)
                    emit(
                        BackendResult.Error(
                            code = 503,
                            message = error.message
                        )
                    )
                } else {
                    val error = json.decodeFromString<BackendError>(responseBody)
                    emit(
                        BackendResult.Error(
                            code = error.errorCode.toInt(),
                            message = error.errorMessage
                        )
                    )
                }
            }
        }.catch { e ->
            emit(BackendResult.Exception(e))
        }
    }

    override fun getCardInformation(cardNumber: String): Flow<BackendResult<Card>> {
        return flow {
            val response: HttpResponse = httpClient.get("card/getInformation/$cardNumber")
            val responseBody = response.bodyAsText()

            if (response.status == HttpStatusCode.OK) {
                val backend = json.decodeFromString<CardInformationResponse>(responseBody)
                emit(BackendResult.Success(backend.toCard()))
            } else if (response.status == HttpStatusCode.ServiceUnavailable) {
                val error = json.decodeFromString<ServiceUnavailable>(responseBody)
                emit(
                    BackendResult.Error(
                        code = 503,
                        message = error.message
                    )
                )
            } else {
                val error = json.decodeFromString<BackendError>(responseBody)
                emit(
                    BackendResult.Error(
                        code = error.errorCode.toInt(),
                        message = error.errorMessage
                    )
                )
            }
        }.catch { e ->
            emit(BackendResult.Exception(e))
        }
    }

    override fun getCardBalance(cardNumber: String): Flow<BackendResult<CardBalance>> {
        return flow {
            val response: HttpResponse = httpClient.get("card/getBalance/$cardNumber")
            val responseBody = response.bodyAsText()

            if (response.status == HttpStatusCode.OK) {
                val backend = json.decodeFromString<CardBalanceResponse>(responseBody)
                emit(BackendResult.Success(backend.toCardBalance()))
            } else if (response.status == HttpStatusCode.ServiceUnavailable) {
                val error = json.decodeFromString<ServiceUnavailable>(responseBody)
                emit(
                    BackendResult.Error(
                        code = 503,
                        message = error.message
                    )
                )
            } else {
                val error = json.decodeFromString<BackendError>(responseBody)
                emit(
                    BackendResult.Error(
                        code = error.errorCode.toInt(),
                        message = error.errorMessage
                    )
                )
            }
        }.catch { e ->
            emit(BackendResult.Exception(e))
        }
    }

}