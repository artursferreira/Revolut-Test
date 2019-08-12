package com.artur.exchangecurrencies.ui.currency

import androidx.lifecycle.MutableLiveData
import com.artur.exchangecurrencies.base.BaseViewModel
import com.artur.exchangecurrencies.model.Currency
import com.artur.exchangecurrencies.model.RateResponse
import com.artur.exchangecurrencies.network.CurrencyApi
import com.artur.exchangecurrencies.utils.countryCodes
import com.artur.exchangecurrencies.utils.currencyNames
import com.artur.exchangecurrencies.utils.getFlagUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Artur on 06/08/2019.
 */
class CurrencyViewModel : BaseViewModel() {

    @Inject
    lateinit var currencyApi: CurrencyApi

    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val rateResponse: MutableLiveData<List<Currency>> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    private var selectedCurrency: Currency

    private lateinit var subscription: Disposable

    init {
        selectedCurrency = Currency(
                "EUR",
                1.0,
                "Euro",
                getFlagUrl("EU"),
                true
        )

        getCurrencies()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun getCurrencies() {
        loadingVisibility.value = true

        subscription = currencyApi.getCurrencies(selectedCurrency.code)
                //   .repeatWhen { handler -> handler.delay(1, TimeUnit.SECONDS) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(
                        { result -> onCurrencyResultSuccess(result) },
                        { onCurrencyResultError() }
                )
    }

    private fun onCurrencyResultSuccess(result: RateResponse) {
        val currencies = mutableListOf<Currency>()
        currencies.add(0, selectedCurrency)
        result.rates.forEach {
            currencies.add(
                    Currency(
                            it.key,
                            it.value * selectedCurrency.value,
                            currencyNames[it.key],
                            getFlagUrl(countryCodes[it.key] ?: "")
                    )
            )
        }
        loadingVisibility.value = false
        rateResponse.postValue(currencies)
    }

    private fun onCurrencyResultError() {

    }

    fun onItemClick(currency: Currency) {
        selectedCurrency = currency
        selectedCurrency.selected = true
        getCurrencies()
    }


    fun onCurrencyValueChanged(value: CharSequence) {
        selectedCurrency.value = if (value.isEmpty()) 0.0
        else try {
            value.toString().replace(",", "").toDouble()
        } catch (e: Exception) {
            return
        }

        //TODO update list values
    }



}