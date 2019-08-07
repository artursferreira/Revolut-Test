package com.artur.exchangecurrencies.ui.currency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.artur.exchangecurrencies.R
import com.artur.exchangecurrencies.databinding.ActivityCurrencyBinding
import com.artur.exchangecurrencies.di.ViewModelFactory
import kotlinx.android.synthetic.main.activity_currency.*

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

    }

}
