package com.murin.dailyflights.data.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class Baggage(
    @field:Json(name = "personal_item")
    val personalItem: BaggageItem?,
    val hand: BaggageItem?,
    val hold: List<BaggageItem>?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(BaggageItem::class.java.classLoader),
        parcel.readParcelable(BaggageItem::class.java.classLoader),
        parcel.createTypedArrayList(BaggageItem)
    )

    data class BaggageItem(
        val weight: Float?,
        val price: Float?
    ): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readValue(Float::class.java.classLoader) as? Float,
            parcel.readValue(Float::class.java.classLoader) as? Float
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeValue(weight)
            parcel.writeValue(price)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<BaggageItem> {
            override fun createFromParcel(parcel: Parcel): BaggageItem {
                return BaggageItem(parcel)
            }

            override fun newArray(size: Int): Array<BaggageItem?> {
                return arrayOfNulls(size)
            }
        }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(personalItem, flags)
        parcel.writeParcelable(hand, flags)
        parcel.writeTypedList(hold)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Baggage> {
        override fun createFromParcel(parcel: Parcel): Baggage {
            return Baggage(parcel)
        }

        override fun newArray(size: Int): Array<Baggage?> {
            return arrayOfNulls(size)
        }
    }
}
