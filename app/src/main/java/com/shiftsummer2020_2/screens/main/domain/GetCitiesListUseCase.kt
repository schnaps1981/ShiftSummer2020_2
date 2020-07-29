package com.shiftsummer2020_2.screens.main.domain

import com.shiftsummer2020_2.screens.main.domain.entities.City

class GetCitiesListUseCase(private val citiesRepository: CitiesRepository) {
    operator fun invoke(): ArrayList<City> = citiesRepository.getCities()

}