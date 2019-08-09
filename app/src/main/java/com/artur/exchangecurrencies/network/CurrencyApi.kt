package com.artur.exchangecurrencies.network

import com.artur.exchangecurrencies.model.RateResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 */
interface CurrencyApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/latest")
    fun getCurrencies(@Query("base") base: String): Observable<RateResponse>
}