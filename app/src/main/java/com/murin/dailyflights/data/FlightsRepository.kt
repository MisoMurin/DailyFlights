package com.murin.dailyflights.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.murin.dailyflights.data.network.RetrofitFactory

class FlightsRepository private constructor() {
    val flights = MutableLiveData<List<Flight>>()
    val fetchStatus = MutableLiveData<FetchStatus>()

    suspend fun fetchFlightsFromApi() =
        try {
            fetchStatus.postValue(FetchStatus.FETCHING)

            RetrofitFactory.flightsApi.getFlights(
                flyFrom = "PRG",
                to = "LGW",
                dateFrom = "18/02/2019",
                dateTo = "25/02/2019",
                limit = 10
            ).await().let {
                if (it.isSuccessful && it.body() != null) {
                    fetchStatus.postValue(FetchStatus.SUCCESS)
                    flights.postValue(it.body()!!.data)
                } else {
                    fetchStatus.postValue(FetchStatus.FAILURE)
                    Log.e("FLIGHTS_ERROR", "${it.code()}: ${it.message()}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            fetchStatus.postValue(FetchStatus.FAILURE)
        }


    companion object {
        @Volatile private var instance: FlightsRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FlightsRepository().also { instance = it }
            }
    }

    enum class FetchStatus {
        SUCCESS(),
        FETCHING(),
        FAILURE()
    }
}
