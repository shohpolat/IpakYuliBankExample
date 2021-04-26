package com.shoh.ipakyolibankexample.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Transfer(
    val date: String,
    val sendingCard: String,
    val receivingCard: String,
    val cardHolder: String,
    val foreignAmount: String,
    val uzbekAmount: String,
    val serviceCharge: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(sendingCard)
        parcel.writeString(receivingCard)
        parcel.writeString(cardHolder)
        parcel.writeString(foreignAmount)
        parcel.writeString(uzbekAmount)
        parcel.writeString(serviceCharge)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Transfer> {
        override fun createFromParcel(parcel: Parcel): Transfer {
            return Transfer(parcel)
        }

        override fun newArray(size: Int): Array<Transfer?> {
            return arrayOfNulls(size)
        }
    }
}
