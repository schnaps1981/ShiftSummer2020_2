package com.shiftsummer2020_2.data.apimodel

import com.shiftsummer2020_2.data.apimodel.Data

data class WeatherParams(
    val count: Int,
    val `data`: List<Data>
)