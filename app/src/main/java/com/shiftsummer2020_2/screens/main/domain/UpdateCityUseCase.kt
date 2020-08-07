package com.shiftsummer2020_2.screens.main.domain

import exapmle.com.common.CityDto

class UpdateCityUseCase(private val citiesRepository: CitiesRepository) {
    suspend operator fun invoke(city: CityDto, id: Int) = citiesRepository.updateCity(city, id)
}