package com.murin.dailyflights.data.network

import com.murin.dailyflights.data.FlightsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightsApi {
    @GET("flights")
    fun getFlights(
        @Query(value = "v", encoded = true) version: Int = 2,
        @Query(value = "flyFrom", encoded = true) flyFrom: String,
        @Query(value = "to", encoded = true) to: String = "anywhere",
        @Query(value = "dateFrom", encoded = true) dateFrom: String,
        @Query(value = "dateTo", encoded = true) dateTo: String,
        @Query(value = "partner", encoded = true) partner: String = "picky",
        @Query(value = "limit", encoded = true) limit: Int
    ): Deferred<Response<FlightsResponse>>
}
