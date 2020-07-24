package com.shiftsummer2020_2.data.appmodel

import com.shiftsummer2020_2.data.apimodel.WeatherParams

data class City (
    val city: Pair<String, WeatherParams?> //название города + погода
)