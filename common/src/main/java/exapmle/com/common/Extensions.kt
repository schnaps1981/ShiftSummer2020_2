package exapmle.com.common

fun City.toCityDto() = CityDto(city = this.city, temperature = this.temperature)