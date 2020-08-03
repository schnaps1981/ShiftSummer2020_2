package exapmle.com.common

import java.io.Serializable

data class City(
    val id : Long,
    val city: String //название города
) : Serializable