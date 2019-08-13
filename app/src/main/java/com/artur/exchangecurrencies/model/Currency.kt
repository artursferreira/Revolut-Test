package com.artur.exchangecurrencies.model

/**
 * Created by Artur on 08/08/2019.
 */
data class Currency(
        val code: String,
        var conversionValue: Double,
        val currencyName: String? = "",
        val flagIcon: String = "",
        var calculatedValue: Double,
        var selected: Boolean = false
)