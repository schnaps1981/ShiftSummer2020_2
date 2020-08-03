package com.shiftsummer2020_2.screens.main.domain

import exapmle.com.common.City

interface CitiesRepository {
    suspend fun getCities() : List<City>
}