package com.shiftsummer2020_2.screens.main.data


import exapmle.com.common.City
import exapmle.com.common.CityDto
import retrofit2.Response
import retrofit2.http.*


interface CitiesApi {
    @GET("/cities")
    suspend fun getAll(): List<City>

    @POST("/cities")
    suspend fun addCity(@Body data: CityDto) : Response<Unit>

    @DELETE("/cities")
    suspend fun deleteById(@Query("id") id: Long) : Response<Unit>

    @PATCH("/cities")
    suspend fun update(@Body city: CityDto, @Query("id") id: Long) : Response<Unit>
}