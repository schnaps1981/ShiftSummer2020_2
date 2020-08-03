package com.shiftsummer2020_2.screens.main.data

import exapmle.com.common.City

interface CitiesDataSource {
    suspend fun getCities(): List<City>
}