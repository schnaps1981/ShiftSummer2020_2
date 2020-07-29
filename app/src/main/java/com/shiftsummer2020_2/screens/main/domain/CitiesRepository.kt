package com.shiftsummer2020_2.screens.main.domain

import com.shiftsummer2020_2.screens.main.domain.entities.City

interface CitiesRepository {
    fun getCities() : ArrayList<City>
}