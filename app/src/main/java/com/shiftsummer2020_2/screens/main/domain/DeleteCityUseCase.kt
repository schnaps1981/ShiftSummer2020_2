package com.shiftsummer2020_2.screens.main.domain

import exapmle.com.common.City

class DeleteCityUseCase(private val citiesRepository: CitiesRepository) {
    suspend operator fun invoke(id: Int): Int = citiesRepository.deleteCity(id)
}