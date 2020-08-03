package com.shiftsummer2020_2.screens.main.data

import City
import com.shiftsummer2020_2.screens.main.domain.CitiesRepository

class CitiesRepositoryImpl(private val citiesDataSource: CitiesDataSource) :
    CitiesRepository {
    override fun getCities(): ArrayList<City> = citiesDataSource.getCities()

}