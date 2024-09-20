package com.example.myapplication.modules.map.data.api

import android.util.Log
import com.example.myapplication.core.backend.BackendResult
import com.example.myapplication.modules.map.data.models.Stop
import com.example.myapplication.modules.map.domain.repository.MapRepository
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

class OpenTripPlannerService @Inject constructor(
    private val httpClient: HttpClient,
    private val json: Json
) : MapRepository {

    override fun getStopsNearLocation(
        latitude: Double,
        longitude: Double,
        radius: Int
    ): Flow<BackendResult<List<Stop>>> {
        return flow {
            val url = "http://sisuotp.tullaveplus.gov.co/otp/routers/default/index/stops?lat=$latitude&lon=$longitude&radius=$radius"
            val response: HttpResponse = httpClient.get(url)
            val responseBody = response.bodyAsText()

            if (response.status == HttpStatusCode.OK) {
                val backend = json.decodeFromString<List<Stop>>(responseBody)
                Log.e("TAG", "getStopsNearLocation: list -> $backend", )
                emit(BackendResult.Success(backend))
            } else {
                emit(
                    BackendResult.Error(
                        code = 500,
                        message = "Hubo un error en la consulta"
                    )
                )
            }
        }.catch { e ->
            emit(BackendResult.Exception(e))
        }
    }
}