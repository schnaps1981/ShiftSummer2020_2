package com.example.server.repository

import City

class CitiesRepository {
        fun getAll() = arrayListOf(
            City("Tomsk" to null),
            City("Novosibirsk" to null),
            City("Rostov-on-Don" to null),
            City("Krasnodar" to null),
            City("Kaliningrad" to null),
            City("Khabarovsk" to null),
            City("Vladivostok" to null)
        )
}