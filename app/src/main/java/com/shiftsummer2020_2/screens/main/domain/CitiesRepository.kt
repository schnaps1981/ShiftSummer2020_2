package com.shiftsummer2020_2.screens.main.domain

import com.shiftsummer2020_2.screens.main.domain.apiwrapper.ApiResultWrapper
import exapmle.com.common.City
import exapmle.com.common.CityDto
import retrofit2.Response

interface CitiesRepository {
    suspend fun getCities() : ApiResultWrapper<List<City>>
    suspend fun addCity(city: CityDto) : ApiResultWrapper<Response<Unit>>
    suspend fun deleteCity(id: Long) : ApiResultWrapper<Response<Unit>>
    suspend fun updateCity(city: CityDto, id: Long): ApiResultWrapper<Response<Unit>>
}