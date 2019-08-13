package com.artur.exchangecurrencies.ui.currency.adapter

import androidx.recyclerview.widget.DiffUtil
import com.artur.exchangecurrencies.model.Currency

/**
 * Created by Artur on 12/08/2019.
 */

class CurrencyDiffUtilCallback(private val oldItems: List<Currency>, private val newItems: List<Currency>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.code == newItem.code && oldItem.selected == newItem.selected
    }

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.calculatedValue == newItem.calculatedValue
    }

}