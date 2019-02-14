package com.murin.dailyflights.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.google.android.material.snackbar.Snackbar
import com.murin.dailyflights.Provider
import com.murin.dailyflights.R
import com.murin.dailyflights.data.FlightsRepository.FetchStatus.*
import com.murin.dailyflights.databinding.FragmentFlightsListBinding

class FlightsListFragment : Fragment() {

    private lateinit var viewModel: FlightsListViewModel
    private lateinit var binding: FragmentFlightsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightsListBinding.inflate(inflater, container, false)

        val factory = Provider.provideFlightsListViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(FlightsListViewModel::class.java)

        val adapter = FlightsAdapter()
        binding.rvFlightsList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: FlightsAdapter) {
        viewModel.flights.observe(viewLifecycleOwner, Observer { fights ->
            val flightsCount = fights?.size ?: 0
            if (flightsCount > 0) {
                adapter.submitList(fights)
            }
        })

        viewModel.fetchStatus.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                SUCCESS -> {}
                FAILURE -> snackbar(R.string.fetch_error)
                FETCHING -> snackbar(R.string.fetching)
                else -> snackbar(R.string.unknown_state)
            }
        })
    }

    private fun snackbar(messageId: Int) =
        Snackbar.make(binding.rvFlightsList, messageId, Snackbar.LENGTH_LONG).show()
}
