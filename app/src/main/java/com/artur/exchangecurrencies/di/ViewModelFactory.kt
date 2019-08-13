package com.artur.exchangecurrencies.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artur.exchangecurrencies.ui.currency.CurrencyViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return CurrencyViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}