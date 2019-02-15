package com.murin.dailyflights.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.murin.dailyflights.data.model.Flight
import com.murin.dailyflights.databinding.ItemFlightBinding

class FlightsAdapter : ListAdapter<Flight, FlightsAdapter.ViewHolder>(FlightDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flight = getItem(position)
        holder.apply {
            bind(createOnClickListener(flight), flight)
            itemView.tag = flight
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFlightBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    private fun createOnClickListener(flight: Flight): View.OnClickListener {
        return View.OnClickListener {
            val direction = FlightsListFragmentDirections
                .actionFlightsFragmentToFlightDetailFragment().setFlight(flight)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: ItemFlightBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Flight) {
            binding.apply {
                clickListener = listener
                flight = item
                executePendingBindings()
            }
        }
    }
}
