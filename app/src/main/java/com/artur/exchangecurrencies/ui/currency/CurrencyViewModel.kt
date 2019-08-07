package com.artur.exchangecurrencies.ui.currency

import androidx.lifecycle.MutableLiveData
import com.artur.exchangecurrencies.base.BaseViewModel
import io.reactivex.disposables.Disposable

/**
 * Created by Artur on 06/08/2019.
 */
class CurrencyViewModel : BaseViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun getCurrencies(base: String) {

    }

}