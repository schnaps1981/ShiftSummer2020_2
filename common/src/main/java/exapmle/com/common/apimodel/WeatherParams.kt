package exapmle.com.common.apimodel

import java.io.Serializable

data class WeatherParams(
    val count: Int,
    val data: List<Data>
): Serializable