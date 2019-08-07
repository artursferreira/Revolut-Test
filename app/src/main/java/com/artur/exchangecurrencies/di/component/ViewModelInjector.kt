package com.artur.exchangecurrencies.di.component

import com.artur.exchangecurrencies.di.module.NetworkModule
import com.artur.exchangecurrencies.ui.currency.CurrencyViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified CurrencyViewModel.
     * @param currencyViewModel CurrencyViewModel in which to inject the dependencies
     */
    fun inject(currencyViewModel: CurrencyViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}