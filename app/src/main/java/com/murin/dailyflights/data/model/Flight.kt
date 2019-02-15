package com.murin.dailyflights.data.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter

data class Flight(
    val id: String?,
    @field:Json(name = "return_duration")
    val returnDuration: String?,
    val flyFrom: String?,
    val flyTo: String?,
    @field:Json(name = "dTime")
    val departureTime: Long,
    @field:Json(name = "aTime")
    val arrivalTime: Long,
    @field:Json(name = "deep_link")
    val deepLink: String?,
    val quality: Float?,
    @field:Json(name = "fly_duration")
    val flyDuration: String?,
    val distance: Float?,
    val bookingToken: String?,
    val cityFrom: String?,
    val countryFrom: Country?,
    val cityTo: String?,
    val countryTo: Country?,
    val duration: Duration?,
    val price: Float?,
    val route: List<Route>?,
    val baggage: Baggage?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Country::class.java.classLoader),
        parcel.readString(),
        parcel.readParcelable(Country::class.java.classLoader),
        parcel.readParcelable(Duration::class.java.classLoader),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.createTypedArrayList(Route),
        parcel.readParcelable(Baggage::class.java.classLoader)
    )

    fun timeString(time: Long): String = DateTimeFormatter.ofPattern("dd.MM.YYYY, HH:mm").format(
        LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.UTC)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(returnDuration)
        parcel.writeString(flyFrom)
        parcel.writeString(flyTo)
        parcel.writeLong(departureTime)
        parcel.writeLong(arrivalTime)
        parcel.writeString(deepLink)
        parcel.writeValue(quality)
        parcel.writeString(flyDuration)
        parcel.writeValue(distance)
        parcel.writeString(bookingToken)
        parcel.writeString(cityFrom)
        parcel.writeParcelable(countryFrom, flags)
        parcel.writeString(cityTo)
        parcel.writeParcelable(countryTo, flags)
        parcel.writeParcelable(duration, flags)
        parcel.writeValue(price)
        parcel.writeTypedList(route)
        parcel.writeParcelable(baggage, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Flight> {
        override fun createFromParcel(parcel: Parcel): Flight {
            return Flight(parcel)
        }

        override fun newArray(size: Int): Array<Flight?> {
            return arrayOfNulls(size)
        }
    }

}
