package exapmle.com.common

import java.io.Serializable

data class CityDto(
    val city: String,
    val temperature: Int
) : Serializable
