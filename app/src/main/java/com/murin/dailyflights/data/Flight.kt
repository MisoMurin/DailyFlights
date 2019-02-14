package com.murin.dailyflights.data

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
    val price: Float?
): Parcelable {

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
        parcel.readValue(Float::class.java.classLoader) as? Float
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

    fun timeString(time: Long): String = DateTimeFormatter.ofPattern("dd.MM.YYYY, HH:mm").format(
        LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.UTC)
    )

    data class Duration(
        val total: Int,
        val `return`: Int,
        val departure: Int
    ): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(total)
            parcel.writeInt(`return`)
            parcel.writeInt(departure)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Duration> {
            override fun createFromParcel(parcel: Parcel): Duration {
                return Duration(parcel)
            }

            override fun newArray(size: Int): Array<Duration?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Country(
        val code: String,
        val name: String
    ): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: ""
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(code)
            parcel.writeString(name)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Country> {
            override fun createFromParcel(parcel: Parcel): Country {
                return Country(parcel)
            }

            override fun newArray(size: Int): Array<Country?> {
                return arrayOfNulls(size)
            }
        }

    }
}
