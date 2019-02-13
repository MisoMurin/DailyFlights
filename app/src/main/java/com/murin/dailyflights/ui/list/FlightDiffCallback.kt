package com.murin.dailyflights.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.murin.dailyflights.data.Flight

class FlightDiffCallback: DiffUtil.ItemCallback<Flight>() {

    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }
}
