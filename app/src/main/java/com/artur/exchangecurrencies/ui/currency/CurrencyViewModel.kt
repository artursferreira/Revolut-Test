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
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Artur on 06/08/2019.
 */
class CurrencyViewModel : BaseViewModel() {

    @Inject
    lateinit var currencyApi: CurrencyApi

    val rateResponse: MutableLiveData<List<Currency>> = MutableLiveData()
    val errorMessage: MutableLiveData<Boolean> = MutableLiveData()

    private var response: RateResponse? = null
    private var selectedCurrency: Currency

    private lateinit var subscription: Disposable

    init {
        selectedCurrency = Currency(
                "EUR",
                1.0,
                "Euro",
                getFlagUrl("EU"),
                1.0,
                true
        )

        getCurrencies()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun getCurrencies() {

        subscription = currencyApi.getCurrencies(selectedCurrency.code)
                .repeatWhen { handler -> handler.delay(1, TimeUnit.SECONDS) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> updateCurrencyResult(result) },
                        { onCurrencyResultError() }
                )
    }

    private fun updateCurrencyResult(result: RateResponse?) {
        this.response = result
        val currencies = result?.rates?.map {
            Currency(
                    it.key,
                    it.value,
                    currencyNames[it.key],
                    getFlagUrl(countryCodes[it.key] ?: ""),
                    it.value * selectedCurrency.calculatedValue)
        }?.filterNot { it.code == selectedCurrency.code }?.toMutableList()
        currencies?.add(0, selectedCurrency)

        rateResponse.postValue(currencies)
    }

    private fun onCurrencyResultError() {
        errorMessage.postValue(true)
    }

    fun onItemClick(currency: Currency) {
        selectedCurrency = currency
        selectedCurrency.selected = true
        getCurrencies()
    }


    fun onCurrencyValueChanged(value: CharSequence) {
        selectedCurrency.calculatedValue = if (value.isEmpty()) 0.0
        else try {
            value.toString().replace(",", "").toDouble()
        } catch (e: Exception) {
            return
        }

        updateCurrencyResult(response)
    }


}