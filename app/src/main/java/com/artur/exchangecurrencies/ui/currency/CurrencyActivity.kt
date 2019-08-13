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
import com.artur.exchangecurrencies.ui.currency.adapter.CurrencyListener
import kotlinx.android.synthetic.main.activity_currency.*

class CurrencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCurrencyBinding
    private lateinit var viewModel: CurrencyViewModel
    private lateinit var currencyListAdapter: CurrencyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_currency)
        setupAdapter()

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(CurrencyViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.rateResponse.observe(this, Observer { onCurrenciesUpdated(it) })

    }

    private fun setupAdapter() {
        currencyListAdapter = CurrencyListAdapter(this, CurrencyListener(
                { viewModel.onCurrencyValueChanged(it) },
                { viewModel.onItemClick(it) }
        ))
        binding.currencyRecyclerView.adapter = currencyListAdapter


    }

    private fun onCurrenciesUpdated(currencies: List<Currency>) {

        currencyListAdapter.updateCurrencyList(currencies)

    }

}
