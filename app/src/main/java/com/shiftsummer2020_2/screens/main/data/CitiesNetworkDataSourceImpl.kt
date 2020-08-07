package com.shiftsummer2020_2.screens.main.data

import com.shiftsummer2020_2.screens.main.domain.apiwrapper.apiCallWrapper
import exapmle.com.common.CityDto

class CitiesNetworkDataSourceImpl(val api: CitiesApi): CitiesNetworkDataSource {
    override suspend fun getCities() = apiCallWrapper { api.getAll() }
    override suspend fun addCity(data: CityDto) = apiCallWrapper { api.addCity(data) }
    override suspend fun deleteById(id: Int) = apiCallWrapper { api.deleteById(id) }
    override suspend fun update(city: CityDto, id: Int) = apiCallWrapper { api.update(city, id) }
}
