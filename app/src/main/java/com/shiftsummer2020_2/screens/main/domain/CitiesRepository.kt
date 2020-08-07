package com.shiftsummer2020_2.screens.main.domain

import com.shiftsummer2020_2.screens.main.domain.apiwrapper.ApiResultWrapper
import exapmle.com.common.City
import exapmle.com.common.CityDto

interface CitiesRepository {
    suspend fun getCities() : ApiResultWrapper<List<City>>
    suspend fun addCity(city: CityDto) : ApiResultWrapper<Int>
    suspend fun deleteCity(id: Int) : ApiResultWrapper<Int>
    suspend fun updateCity(city: CityDto, id: Int): ApiResultWrapper<Int>
}