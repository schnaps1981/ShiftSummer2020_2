package com.shiftsummer2020_2.screens.main.data

import City

interface CitiesDataSource {
    fun getCities() : ArrayList<City>
}

class CitiesDataSourceImpl :
    CitiesDataSource {
    override fun getCities(): ArrayList<City> = arrayListOf(
        City(id = 1, city = "Tomsk"),
        City(id = 2, city = "Novosibirsk"),
        City(id = 3, city = "Rostov-on-Don"),
        City(id = 4, city = "Krasnodar"),
        City(id = 5, city = "Kaliningrad"),
        City(id = 6, city = "Khabarovsk"),
        City(id = 7, city = "Vladivostok")
    )

}