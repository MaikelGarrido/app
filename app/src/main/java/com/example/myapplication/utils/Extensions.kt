package com.example.myapplication.utils

fun String.formatAsCardNumber(): String {
    return this.chunked(4).joinToString(" ")
}
