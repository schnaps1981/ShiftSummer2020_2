package com.shiftsummer2020_2.screens.main.data

import com.shiftsummer2020_2.screens.main.domain.CitiesRepository
import exapmle.com.common.CityDto

class CitiesRepositoryImpl(private val citiesNetworkDataSource: CitiesNetworkDataSource): CitiesRepository {
    override suspend fun getCities() = citiesNetworkDataSource.getCities()
    override suspend fun addCity(city: CityDto) = citiesNetworkDataSource.addCity(city)
    override suspend fun deleteCity(id: Long) = citiesNetworkDataSource.deleteById(id)
    override suspend fun updateCity(city: CityDto, id: Long) = citiesNetworkDataSource.update(city, id)


}