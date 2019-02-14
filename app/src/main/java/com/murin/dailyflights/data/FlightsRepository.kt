package com.murin.dailyflights.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.murin.dailyflights.data.network.RetrofitFactory
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class FlightsRepository private constructor() {
    val flights = MutableLiveData<List<Flight>>()
    val fetchStatus = MutableLiveData<FetchStatus>()

    suspend fun fetchFlightsFromApi() =
        try {
            fetchStatus.postValue(FetchStatus.FETCHING)

            RetrofitFactory.flightsApi.getFlights(
                flyFrom = "PRG",
                dateFrom = DateTimeFormatter.ofPattern("dd/MM/YYYY").format(LocalDate.now()),
                dateTo = DateTimeFormatter.ofPattern("dd/MM/YYYY").format(LocalDate.now().plusDays(3)),
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
