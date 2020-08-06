package com.shiftsummer2020_2.screens.main.domain

import exapmle.com.common.City
import exapmle.com.common.CityDto

interface CitiesRepository {
    suspend fun getCities() : List<City>
    suspend fun addCity(city: CityDto) : Int
    suspend fun deleteCity(id: Int) : Int
    suspend fun updateCity(city: CityDto, id: Int): Int
}