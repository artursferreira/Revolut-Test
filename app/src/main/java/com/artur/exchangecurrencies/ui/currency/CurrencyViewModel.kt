package com.artur.exchangecurrencies.ui.currency

import androidx.lifecycle.MutableLiveData
import com.artur.exchangecurrencies.base.BaseViewModel
import com.artur.exchangecurrencies.model.RateResponse
import com.artur.exchangecurrencies.network.CurrencyApi
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

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val rateResponse: MutableLiveData<RateResponse> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {
        getCurrencies("EUR")
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun getCurrencies(base: String) {

        subscription = currencyApi.getCurrencies(base)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(
                        { result -> onCurrencyResultSuccess(result) },
                        { onCurrencyResultError() }
                )
    }

    private fun onCurrencyResultSuccess(result: RateResponse) {
        rateResponse.postValue(result)
    }

    private fun onCurrencyResultError() {

    }

}