package com.murin.dailyflights.di

import com.murin.dailyflights.data.FlightsRepository
import com.murin.dailyflights.ui.list.FlightsListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel {
        FlightsListViewModel(get<FlightsRepository>())
    }
}
