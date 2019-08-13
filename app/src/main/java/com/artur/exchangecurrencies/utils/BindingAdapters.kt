package com.artur.exchangecurrencies.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.NumberFormat

/**
 * Created by Artur on 12/08/2019.
 */

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