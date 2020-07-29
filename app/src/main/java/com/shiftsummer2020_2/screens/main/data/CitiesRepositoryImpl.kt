package com.shiftsummer2020_2.screens.main.data

import com.shiftsummer2020_2.screens.main.domain.entities.City
import com.shiftsummer2020_2.screens.main.domain.CitiesRepository

class CitiesRepositoryImpl(private val citiesDataSource: CitiesDataSource) :
    CitiesRepository {
    override fun getCities(): ArrayList<City> = citiesDataSource.getCities()

}