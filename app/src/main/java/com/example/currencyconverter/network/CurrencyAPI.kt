package com.example.currencyconverter.network

import com.example.currencyconverter.model.CurrencyItem
import com.example.currencyconverter.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyAPI {

    @GET("latest")
    suspend fun getCurrencyUpdates(
        @Query("access_key")
        access_key: String = API_KEY,
        @Query("base")
        base: String = "EUR",
        @Query("symbol")
        symbol: String
    ) : Response<CurrencyItem>

}