package com.osamaaftab.dindinn.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Menu(val menuList: List<MenuItem>): Parcelable
