package com.artur.exchangecurrencies.utils

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.artur.exchangecurrencies.utils.extensions.getParentActivity
import java.text.NumberFormat

/**
 * Created by Artur on 12/08/2019.
 */

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value ->
            view.visibility = value ?: View.VISIBLE
        })
    }
}

@BindingAdapter("currencyValue")
fun bindEditTextCurrencyValue(editText: TextView, rateValue: Double?) {
    rateValue?.let {
        val numberFormat = NumberFormat.getNumberInstance()
        numberFormat.maximumFractionDigits = 2
        //numberFormat.isGroupingUsed = false
        val roundedRateValue = numberFormat.format(rateValue)
        editText.text = roundedRateValue
    }
}