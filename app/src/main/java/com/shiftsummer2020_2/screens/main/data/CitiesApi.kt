package com.shiftsummer2020_2.screens.main.data


import exapmle.com.common.City
import exapmle.com.common.CityDto
import retrofit2.http.*

interface CitiesApi {
    @GET("/cities")
    suspend fun getAll(): List<City>

    @POST("/cities")
    suspend fun addCity(@Body data: CityDto) : Int

    @DELETE("/cities")
    suspend fun deleteById(id: Int) : Int

    @PATCH("/cities")
    suspend fun update(@Body city: CityDto, id: Int) : Int
}