package com.murin.dailyflights.data

import com.squareup.moshi.Json

data class Flight(
    val id: String,
    val mapIdfrom: String,
    @field:Json(name = "return_duration")
    val returnDuration: String,
    val flyTo: String,
    @field:Json(name = "deep_link")
    val deepLink: String
)
