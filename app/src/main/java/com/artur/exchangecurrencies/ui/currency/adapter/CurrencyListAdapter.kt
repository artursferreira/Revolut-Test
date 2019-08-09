package com.artur.exchangecurrencies.ui.currency.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.artur.exchangecurrencies.R
import com.artur.exchangecurrencies.databinding.CurrencyItemBinding
import com.artur.exchangecurrencies.model.Currency

/**
 * Created by Artur on 08/08/2019.
 */
class CurrencyListAdapter(private var context: Context) : RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {

    private lateinit var currencyList: List<Currency>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CurrencyItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.currency_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    override fun getItemCount(): Int {
        return if (::currencyList.isInitialized) currencyList.size else 0
    }

    fun updateCurrencyList(currencyList: List<Currency>) {
        this.currencyList = currencyList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: CurrencyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            binding.currencyCode.text = currency.code
            binding.currencyName.text = currency.currencyName
            binding.currencyValue.setText(currency.value)
        }
    }
}