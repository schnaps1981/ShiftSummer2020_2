package com.shiftsummer2020_2.screens.main.domain

import City

interface CitiesRepository {
    fun getCities() : ArrayList<City>
}