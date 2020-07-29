package com.shiftsummer2020_2.screens.main.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiftsummer2020_2.screens.main.domain.entities.City
import com.shiftsummer2020_2.screens.main.domain.GetCitiesListUseCase
import com.shiftsummer2020_2.global.viewmodel.SingleLiveEvent

class CityListViewModel(
    private val getCitiesListUseCase: GetCitiesListUseCase
) : ViewModel() {

    private val cityListSource = getCitiesListUseCase()

    val cityList = MutableLiveData<List<City>>()
    val cityClickEvent = SingleLiveEvent<City>()
    val cityAddEvent = SingleLiveEvent<City>()

    init {
        cityList.value = cityListSource
    }

    fun cityClicked(city: City) {
        cityClickEvent(city)
    }

    fun cityAdded(city: City) {
        cityListSource.add(city)
        cityList.value = cityListSource
        cityAddEvent(city)
    }
}