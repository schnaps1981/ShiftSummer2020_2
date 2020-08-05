package exapmle.com.common.apimodel

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("city_name")
    val cityName: String,

    val clouds: Int,
    val pres: Double,
    val temp: Double,
    val weather: Weather
)
