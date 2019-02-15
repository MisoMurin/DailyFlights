package com.murin.dailyflights.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.murin.dailyflights.data.model.Flight
import com.murin.dailyflights.data.network.FlightsApi
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class FlightsRepository private constructor() {
    val flights = MutableLiveData<List<Flight>>()
    val fetchStatus = MutableLiveData<FetchStatus>()
    private lateinit var flightsApi: FlightsApi

    suspend fun fetchFlightsFromApi() =
        try {
            fetchStatus.postValue(FetchStatus.FETCHING)

            flightsApi.getFlights(
                flyFrom = "49.2-16.43-250km",
                dateFrom = DateTimeFormatter.ofPattern("dd/MM/YYYY").format(LocalDate.now()),
                dateTo = DateTimeFormatter.ofPattern("dd/MM/YYYY").format(LocalDate.now().plusDays(1)),
                limit = 5
            ).await().let { response ->
                if (response.isSuccessful && response.body() != null) {
                    fetchStatus.postValue(FetchStatus.SUCCESS)
                    flights.postValue(response.body()!!.data.sortedBy { it.departureTime })
                } else {
                    fetchStatus.postValue(FetchStatus.FAILURE)
                    Log.e("FLIGHTS_ERROR", "${response.code()}: ${response.message()}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            fetchStatus.postValue(FetchStatus.FAILURE)
        }


    companion object {
        @Volatile private var instance: FlightsRepository? = null

        fun getInstance(flightsApi: FlightsApi) =
            instance ?: synchronized(this) {
                instance ?: FlightsRepository().also {
                    instance = it
                    (instance as FlightsRepository).flightsApi = flightsApi
                }
            }
    }

    enum class FetchStatus {
        SUCCESS(),
        FETCHING(),
        FAILURE()
    }
}
