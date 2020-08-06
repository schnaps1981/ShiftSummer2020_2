package com.shiftsummer2020_2.screens.main.data

import exapmle.com.common.City
import exapmle.com.common.CityDto

interface CitiesNetworkDataSource {
    suspend fun getCities(): List<City>
    suspend fun addCity(data: CityDto) : Int
    suspend fun deleteById(id: Int) : Int
    suspend fun update(city: CityDto, id: Int) : Int
}