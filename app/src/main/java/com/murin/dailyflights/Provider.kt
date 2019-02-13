package com.murin.dailyflights

import com.murin.dailyflights.data.FlightsRepository
import com.murin.dailyflights.ui.list.FlightsListViewModelFactory

object Provider {
    private fun getFlightsRepository(): FlightsRepository {
        return FlightsRepository.getInstance()
    }

    fun provideFlightsListViewModelFactory(): FlightsListViewModelFactory {
        val repository = getFlightsRepository()
        return FlightsListViewModelFactory(repository)
    }
}
