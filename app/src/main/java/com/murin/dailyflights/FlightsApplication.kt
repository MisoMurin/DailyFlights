package com.murin.dailyflights

import android.app.Application
import com.mapbox.mapboxsdk.Mapbox
import com.murin.dailyflights.di.apiModule
import com.murin.dailyflights.di.flightsModule
import com.murin.dailyflights.di.viewModelModule
import org.koin.android.ext.android.startKoin

class FlightsApplication: Application() {
    companion object {
        const val KIWI_BASE_URL = "kiwi_base_url"
    }

    override fun onCreate() {
        super.onCreate()

        Mapbox.getInstance(this, mapboxToken)

        startKoin(
            this,
            listOf(viewModelModule, apiModule, flightsModule),
            mapOf(KIWI_BASE_URL to "https://api.skypicker.com/")
        )
    }
}
