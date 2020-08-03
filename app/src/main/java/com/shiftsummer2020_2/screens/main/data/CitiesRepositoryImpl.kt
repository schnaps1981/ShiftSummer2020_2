package com.shiftsummer2020_2.screens.main.data

import com.shiftsummer2020_2.screens.main.domain.CitiesRepository
import exapmle.com.common.City

class CitiesRepositoryImpl(private val citiesDataSource: CitiesDataSource): CitiesRepository {
    override suspend fun getCities(): List<City> = citiesDataSource.getCities()
}