package com.shiftsummer2020_2.data.appmodel

import android.os.Parcelable
import com.shiftsummer2020_2.data.apimodel.WeatherParams
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val city: Pair<String, WeatherParams?> //название города + погода
) : Parcelable