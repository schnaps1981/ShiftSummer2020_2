package com.shiftsummer2020_2.screens.main.data

import com.shiftsummer2020_2.screens.main.domain.apiwrapper.ApiResultWrapper
import exapmle.com.common.City
import exapmle.com.common.CityDto
import retrofit2.Response

interface CitiesNetworkDataSource {
    suspend fun getCities(): ApiResultWrapper<List<City>>
    suspend fun addCity(data: CityDto): ApiResultWrapper<Response<Unit>>
    suspend fun deleteById(id: Long): ApiResultWrapper<Response<Unit>>
    suspend fun update(city: CityDto, id: Long): ApiResultWrapper<Response<Unit>>

}