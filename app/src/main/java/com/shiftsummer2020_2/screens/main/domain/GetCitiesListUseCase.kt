package com.shiftsummer2020_2.screens.main.domain

import City


class GetCitiesListUseCase(private val citiesRepository: CitiesRepository) {
    operator fun invoke(): ArrayList<City> = citiesRepository.getCities()

}