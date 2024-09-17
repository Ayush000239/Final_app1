package com.example.final_app.model

import android.os.Parcel
import android.os.Parcelable

data class DataClass(
    val name: String?,
    val culture: String?,
    val domain: String?,
    val symbol: String?,
    val parentage: String?,
    val romanEquivalent: String?, // Ensure this field matches your data
    val description: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(culture)
        parcel.writeString(domain)
        parcel.writeString(symbol)
        parcel.writeString(parentage)
        parcel.writeString(romanEquivalent)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataClass> {
        override fun createFromParcel(parcel: Parcel): DataClass {
            return DataClass(parcel)
        }

        override fun newArray(size: Int): Array<DataClass?> {
            return arrayOfNulls(size)
        }
    }
}
