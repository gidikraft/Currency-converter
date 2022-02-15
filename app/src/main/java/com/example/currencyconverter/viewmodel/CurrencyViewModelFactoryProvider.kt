package com.example.currencyconverter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.repository.CurrencyRepository

class CurrencyViewModelFactoryProvider(
    val currencyRepository: CurrencyRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencyViewModel(currencyRepository) as T
    }
}