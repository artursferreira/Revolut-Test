package com.artur.exchangecurrencies.base

import androidx.lifecycle.ViewModel
import com.artur.exchangecurrencies.di.component.DaggerViewModelInjector
import com.artur.exchangecurrencies.di.component.ViewModelInjector
import com.artur.exchangecurrencies.di.module.NetworkModule
import com.artur.exchangecurrencies.ui.currency.CurrencyViewModel


abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CurrencyViewModel -> injector.inject(this)
        }
    }
}