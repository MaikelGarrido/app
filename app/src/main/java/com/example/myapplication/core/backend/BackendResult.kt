package com.example.myapplication.core.backend

sealed class BackendResult<out T> {
    data class Success<out T>(val data: T?) : BackendResult<T>()
    data class Error<out T>(val code: Int, val message: String?) : BackendResult<T>()
    data class Exception<out T>(val e: Throwable) : BackendResult<T>()
}