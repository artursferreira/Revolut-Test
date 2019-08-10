package com.artur.exchangecurrencies.ui.currency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.artur.exchangecurrencies.R
import com.artur.exchangecurrencies.databinding.ActivityCurrencyBinding
import com.artur.exchangecurrencies.di.ViewModelFactory
import com.artur.exchangecurrencies.model.Currency
import com.artur.exchangecurrencies.model.RateResponse
import com.artur.exchangecurrencies.ui.currency.adapter.CurrencyListAdapter
import com.artur.exchangecurrencies.utils.countryCodes
import com.artur.exchangecurrencies.utils.currencyNames
import kotlinx.android.synthetic.main.activity_currency.*
import com.blongho.country_data.World

class CurrencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCurrencyBinding
    private lateinit var viewModel: CurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        setSupportActionBar(toolbar)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_currency)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(CurrencyViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.rateResponse.observe(this, Observer { setupAdapter(it) })

        World.init(application)

    }

    private fun setupAdapter(rateResponse: RateResponse) {
        val currencyListAdapter = CurrencyListAdapter(this)
        binding.currencyRecyclerView.adapter = currencyListAdapter

        //TODO remove this
        val currencies = mutableListOf<Currency>()
        rateResponse.rates.forEach {
            currencies.add(
                Currency(it.key,
                it.value.toString(),
                    currencyNames[it.key],
                    World.getFlagOf(countryCodes[it.key] ?: "")))
        }

        currencyListAdapter.updateCurrencyList(currencies)

    }

}
