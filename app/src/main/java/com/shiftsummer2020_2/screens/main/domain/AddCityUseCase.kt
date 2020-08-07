package com.shiftsummer2020_2.screens.main.domain

import exapmle.com.common.CityDto

class AddCityUseCase(private val citiesRepository: CitiesRepository) {
    suspend operator fun invoke(city: CityDto) = citiesRepository.addCity(city)
}