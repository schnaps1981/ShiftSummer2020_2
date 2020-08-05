package com.example.server.repository

import com.example.server.Constants
import com.example.server.utils.ConverterRawToCurrent
import com.google.gson.Gson
import exapmle.com.common.apimodel.WeatherParams
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class WeatherAPIRepository(private val httpClient: HttpClient, private val citiesRepository: CitiesRepository) {
    suspend fun updateWeather()
    {
        val cities = citiesRepository.getAll()
        cities.forEach {
            val city = it.city
            val id = it.id

            val rawResponse = httpClient.get<String>("https://api.weatherbit.io/v2.0/current?key=${Constants.API_KEY_WEATHER}&lang=ru&city=${city}")
            val currentWeather = Gson().fromJson(rawResponse, WeatherParams::class.java)

            citiesRepository.update(id, ConverterRawToCurrent.convert(currentWeather))
        }
    }
}