package com.example.currencyconverter.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.model.CurrencyItem
import com.example.currencyconverter.utils.Resource
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.launch


class CurrencyViewModel(private val currencyRepository: CurrencyRepository): ViewModel() {
    //initialise network response to observable livedata
    val currencyUpdates : MutableLiveData<Response<CurrencyItem>> = MutableLiveData()

    init {
        getCurrencyUpdates("EUR")
    }

    //launch the api call form the suspend fun in repository
    private fun getCurrencyUpdates(base: String) = viewModelScope.launch {
        val response = currencyRepository.getCurrencyUpdates(base,"EUR")
        currencyUpdates.value = response

    }

}