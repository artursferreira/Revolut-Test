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
import com.artur.exchangecurrencies.ui.currency.adapter.CurrencyListAdapter
import kotlinx.android.synthetic.main.activity_currency.*

class CurrencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCurrencyBinding
    private lateinit var viewModel: CurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_currency)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(CurrencyViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.rateResponse.observe(this, Observer { setupAdapter(it) })

    }

    private fun setupAdapter(currencies: List<Currency>) {
        val currencyListAdapter = CurrencyListAdapter(this)
        binding.currencyRecyclerView.adapter = currencyListAdapter


        currencyListAdapter.updateCurrencyList(currencies)

    }

}
