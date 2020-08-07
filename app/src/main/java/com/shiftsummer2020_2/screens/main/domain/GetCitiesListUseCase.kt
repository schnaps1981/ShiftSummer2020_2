package com.shiftsummer2020_2.screens.main.domain

import exapmle.com.common.City

class GetCitiesListUseCase(private val citiesRepository: CitiesRepository) {
    suspend operator fun invoke() = citiesRepository.getCities()
}