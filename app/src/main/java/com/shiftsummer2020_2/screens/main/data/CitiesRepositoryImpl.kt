package com.shiftsummer2020_2.screens.main.data

import com.shiftsummer2020_2.screens.main.domain.CitiesRepository
import exapmle.com.common.City
import exapmle.com.common.CityDto

class CitiesRepositoryImpl(private val citiesDataSource: CitiesNetworkDataSource): CitiesRepository {
    override suspend fun getCities(): List<City> = citiesDataSource.getCities()
    override suspend fun addCity(city: CityDto) = citiesDataSource.addCity(city)
    override suspend fun deleteCity(id: Int) = citiesDataSource.deleteById(id)
    override suspend fun updateCity(city: CityDto, id: Int) = citiesDataSource.update(city, id)
}