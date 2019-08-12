package com.artur.exchangecurrencies.model

/**
 * Created by Artur on 08/08/2019.
 */
data class Currency(
        val code: String,
        val value: String,
        val currencyName: String? = "",
        val flagIcon: String = ""
)