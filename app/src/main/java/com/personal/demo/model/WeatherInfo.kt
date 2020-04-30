package com.personal.demo.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class WeatherInfo(
    var success: String,
    var records: Record
) : Parcelable