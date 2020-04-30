package com.personal.demo.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class Location(
    var locationName: String,
    var weatherElement: List<WeatherElement>
) : Parcelable