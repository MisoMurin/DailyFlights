package com.murin.dailyflights.ui.list

import androidx.lifecycle.ViewModel
import com.murin.dailyflights.data.FlightsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FlightsListViewModel internal constructor(
    flightsRepository: FlightsRepository
) : ViewModel() {
    val flights = flightsRepository.flights
    val fetchStatus = flightsRepository.fetchStatus

    init {
        GlobalScope.launch {
            flightsRepository.fetchFlightsFromApi()
        }
    }
}
