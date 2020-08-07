package com.shiftsummer2020_2.screens.main.domain

class DeleteCityUseCase(private val citiesRepository: CitiesRepository) {
    suspend operator fun invoke(id: Long) = citiesRepository.deleteCity(id)
}