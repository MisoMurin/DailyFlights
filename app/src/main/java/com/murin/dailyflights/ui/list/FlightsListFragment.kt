package com.murin.dailyflights.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.murin.dailyflights.R
import com.murin.dailyflights.data.FlightsRepository.FetchStatus.*
import com.murin.dailyflights.databinding.FragmentFlightsListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlightsListFragment : Fragment() {
    private val viewModel: FlightsListViewModel by viewModel()
    private val adapter: FlightsAdapter by inject()
    private lateinit var binding: FragmentFlightsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightsListBinding.inflate(inflater, container, false)

        binding.rvFlightsList.adapter = adapter
        subscribeUi()

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi() {
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
