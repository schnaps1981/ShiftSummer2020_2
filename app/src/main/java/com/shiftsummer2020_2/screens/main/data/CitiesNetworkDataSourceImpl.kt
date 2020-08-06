package com.shiftsummer2020_2.screens.main.data

import exapmle.com.common.City
import exapmle.com.common.CityDto

class CitiesNetworkDataSourceImpl(val api: CitiesApi): CitiesNetworkDataSource {
    override suspend fun getCities(): List<City> = api.getAll()
    override suspend fun addCity(data: CityDto) = api.addCity(data)
    override suspend fun deleteById(id: Int) = api.deleteById(id)
    override suspend fun update(city: CityDto, id: Int) = api.update(city, id)
}