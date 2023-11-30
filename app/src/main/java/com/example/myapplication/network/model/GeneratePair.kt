package com.example.myapplication.network.model
import android.os.Parcel
import android.os.Parcelable


data class GeneratePair(
    val id: Int,
    val pairCode: String,
    val expiresAt: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(pairCode)
        parcel.writeString(expiresAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GeneratePair> {
        override fun createFromParcel(parcel: Parcel): GeneratePair {
            return GeneratePair(parcel)
        }

        override fun newArray(size: Int): Array<GeneratePair?> {
            return arrayOfNulls(size)
        }
    }
}
