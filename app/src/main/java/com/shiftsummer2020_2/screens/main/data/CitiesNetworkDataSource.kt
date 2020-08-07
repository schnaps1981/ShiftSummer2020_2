package com.shiftsummer2020_2.screens.main.data

import com.shiftsummer2020_2.screens.main.domain.apiwrapper.ApiResultWrapper
import exapmle.com.common.City
import exapmle.com.common.CityDto

interface CitiesNetworkDataSource {
    suspend fun getCities(): ApiResultWrapper<List<City>>
    suspend fun addCity(data: CityDto): ApiResultWrapper<Int>
    suspend fun deleteById(id: Int): ApiResultWrapper<Int>
    suspend fun update(city: CityDto, id: Int): ApiResultWrapper<Int>

}