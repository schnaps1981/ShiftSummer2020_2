package com.shiftsummer2020_2.screens.main.data

import exapmle.com.common.City

class CitiesDataSourceImpl(val api: CitiesApi): CitiesDataSource {
    override suspend fun getCities(): List<City> = api.getAll()
}