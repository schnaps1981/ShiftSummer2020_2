package com.shiftsummer2020_2.screens.main.domain

class GetCitiesListUseCase(private val citiesRepository: CitiesRepository) {
    suspend operator fun invoke() = citiesRepository.getCities()
}