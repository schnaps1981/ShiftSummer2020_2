package com.shiftsummer2020_2.screens.main.data


import exapmle.com.common.City
import retrofit2.http.GET

interface CitiesApi {
    @GET("/cities")
    suspend fun getAll(): List<City>
}