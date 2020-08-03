package com.shiftsummer2020_2.screens.main.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiftsummer2020_2.screens.main.domain.GetCitiesListUseCase
import com.shiftsummer2020_2.global.viewmodel.SingleLiveEvent
import exapmle.com.common.City
import kotlinx.coroutines.launch

class CityListViewModel(
    getCitiesListUseCase: GetCitiesListUseCase
) : ViewModel() {

    val cityList = MutableLiveData<List<City>?>()
    val cityClickEvent = SingleLiveEvent<City>()
    val cityAddEvent = SingleLiveEvent<City>()

    init {
        viewModelScope.launch {
            try {
                cityList.value = getCitiesListUseCase()
            } catch (e: Exception) {
                // show error
            }
        }
    }

    fun cityClicked(city: City) {
        cityClickEvent(city)
    }

    fun cityAdded(city: City) {
        //cityListSource.add(city)
//        cityList.value = cityListSource()
//        cityAddEvent(city)
    }
}