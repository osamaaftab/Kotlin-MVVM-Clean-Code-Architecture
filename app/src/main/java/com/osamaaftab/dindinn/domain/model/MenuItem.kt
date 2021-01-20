package com.osamaaftab.dindinn.domain.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MenuItem(
    val title: String,
    val description:String,
    val image_url : String,
    val type:String,
    val rating :Int,
    val price : Float
) : Parcelable