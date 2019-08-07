package com.artur.exchangecurrencies.model

data class RateResponse(
        var base: String,
        var date: String,
        var rates: Rates
)