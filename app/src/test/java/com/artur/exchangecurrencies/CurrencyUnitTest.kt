package com.artur.exchangecurrencies

import com.artur.exchangecurrencies.utils.countryCodes
import com.artur.exchangecurrencies.utils.currencyNames
import org.junit.Assert
import org.junit.Test

/**
 * Created by Artur on 13/08/2019.
 */
class CurrencyUnitTest {

    @Test
    fun convertCountryCode_isCorrect() {
        Assert.assertEquals(countryCodes["EUR"], "EU")
    }

    @Test
    fun convertCountryName_isCorrect() {
        Assert.assertEquals(currencyNames["EUR"], "Euro")
    }
}