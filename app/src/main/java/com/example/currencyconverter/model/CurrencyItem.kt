package com.example.currencyconverter.model

data class CurrencyItem(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int

)