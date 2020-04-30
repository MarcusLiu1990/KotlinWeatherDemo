package com.personal.demo.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class Record(
    var datasetDescription: String,
    var location: List<Location>
) : Parcelable
