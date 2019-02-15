package com.murin.dailyflights.data.model

import android.os.Parcel
import android.os.Parcelable

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
        with(parcel) {
            writeInt(total)
            writeInt(`return`)
            writeInt(departure)
        }
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
