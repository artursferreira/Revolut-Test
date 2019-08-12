package com.artur.exchangecurrencies.ui.currency.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.artur.exchangecurrencies.R
import com.artur.exchangecurrencies.databinding.CurrencyItemBinding
import com.artur.exchangecurrencies.model.Currency
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * Created by Artur on 08/08/2019.
 */
class CurrencyListAdapter(private var context: Context, private val currencyListener: CurrencyListener) : RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {

    private lateinit var currencyList: List<Currency>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CurrencyItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.currency_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, currencyList[position], currencyListener)
    }

    override fun getItemCount(): Int {
        return if (::currencyList.isInitialized) currencyList.size else 0
    }

    fun updateCurrencyList(currencyList: List<Currency>) {
        this.currencyList = currencyList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: CurrencyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, currency: Currency, currencyListener: CurrencyListener) {
            binding.currencyCode.text = currency.code
            binding.currencyName.text = currency.currencyName
            binding.currencyValue.setText(currency.value.toString())

            binding.currency = currency
            binding.listener = currencyListener
            binding.executePendingBindings()

            Glide.with(context)
                .load(currency.flagIcon)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.countryFlag)

            if (adapterPosition != RecyclerView.NO_POSITION) {
                binding.currencyValue.isClickable = adapterPosition == 0
            }
        }
    }
}

class CurrencyListener(
        private val onTextChangedListener: (value: CharSequence) -> Unit,
        private val onClickListener: (currency: Currency) -> Unit
) {

    fun onItemClick(currency: Currency) {
        if (!currency.selected) {
            onClickListener(currency)
        }
    }

    fun onTextChanged(s: CharSequence, currency: Currency) {
        if (currency.selected) {
            onTextChangedListener(s)
        }
    }


}