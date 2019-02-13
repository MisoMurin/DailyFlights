package com.murin.dailyflights.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.murin.dailyflights.data.FlightsRepository


class FlightsListViewModelFactory(
    private val flightsRepository: FlightsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        FlightsListViewModel(flightsRepository) as T
}
