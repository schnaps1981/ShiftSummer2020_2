package com.example.server.utils

import exapmle.com.common.CityDto
import exapmle.com.common.apimodel.WeatherParams

object ConverterRawToCurrent {
    fun convert(weatherParams: WeatherParams) : CityDto {
        return CityDto(
            city = weatherParams.data.first().cityName
        )
    }
}