package com.shiftsummer2020_2.screens.main.data

import com.shiftsummer2020_2.screens.main.domain.entities.City

interface CitiesDataSource {
    fun getCities() : ArrayList<City>
}

class CitiesDataSourceImpl :
    CitiesDataSource {
    override fun getCities(): ArrayList<City> = arrayListOf(
        City("Tomsk" to null),
        City("Novosibirsk" to null),
        City("Rostov-on-Don" to null),
        City("Krasnodar" to null),
        City("Kaliningrad" to null),
        City("Khabarovsk" to null),
        City("Vladivostok" to null)
    )

}