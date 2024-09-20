package com.example.myapplication.core.backend

import kotlinx.serialization.Serializable

@Serializable
data class BackendError(
    val errorCode: String,
    val errorMessage: String
)

@Serializable
data class ServiceUnavailable(
    val message: String,
)