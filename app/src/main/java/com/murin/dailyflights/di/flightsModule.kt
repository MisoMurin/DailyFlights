package com.murin.dailyflights.di

import com.murin.dailyflights.ui.list.FlightsAdapter
import org.koin.dsl.module.module

val flightsModule = module {
    single {
        FlightsAdapter()
    }
}
