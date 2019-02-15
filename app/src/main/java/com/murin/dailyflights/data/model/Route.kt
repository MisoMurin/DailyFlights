package com.murin.dailyflights.data.model

import android.os.Parcel
import android.os.Parcelable

data class Route(
    val latFrom: Float,
    val lngFrom: Float,
    val latTo: Float,
    val lngTo: Float
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeFloat(latFrom)
            writeFloat(lngFrom)
            writeFloat(latTo)
            writeFloat(lngTo)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Route> {
        override fun createFromParcel(parcel: Parcel): Route {
            return Route(parcel)
        }

        override fun newArray(size: Int): Array<Route?> {
            return arrayOfNulls(size)
        }
    }
}
