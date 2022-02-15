package com.example.currencyconverter.repository

import com.example.currencyconverter.network.RetrofitInstance

class CurrencyRepository {
    //abstract network call from the viewModel
    suspend fun getCurrencyUpdates(base: String, symbol: String) =
        RetrofitInstance.api.getCurrencyUpdates(base=base, symbol=symbol)
}