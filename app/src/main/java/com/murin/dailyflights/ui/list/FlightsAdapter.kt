package com.murin.dailyflights.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.murin.dailyflights.data.Flight
import com.murin.dailyflights.databinding.ItemFlightBinding

class FlightsAdapter : ListAdapter<Flight, FlightsAdapter.ViewHolder>(FlightDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flight = getItem(position)
        holder.apply {
            bind(createOnClickListener(flight.id, position), flight)
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

    private fun createOnClickListener(flightId: String, position: Int): View.OnClickListener {
        return View.OnClickListener {
//            if (getItem(position).hasLandingLocation()) {
//                val direction = MeteorsListFragmentDirections
//                    .ActionMeteorsFragmentToMeteorLandingMapFragment().setMeteorId(meteorId)
//                it.findNavController().navigate(direction)
//            } else {
//                Snackbar.make(it, R.string.no_landing_location, Snackbar.LENGTH_LONG).show()
//            }
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
