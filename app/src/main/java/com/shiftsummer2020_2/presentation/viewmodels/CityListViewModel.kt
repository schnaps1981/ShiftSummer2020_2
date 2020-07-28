package com.shiftsummer2020_2.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shiftsummer2020_2.data.appmodel.City
import com.shiftsummer2020_2.presentation.viewmodels.global.SingleLiveEvent

class CityListViewModel : ViewModel() {

    private val cityListSource = arrayListOf(
        City("Tomsk" to null),
        City("Novosibirsk" to null),
        City("Rostov-on-Don" to null),
        City("Krasnodar" to null),
        City("Kaliningrad" to null),
        City("Khabarovsk" to null),
        City("Vladivostok" to null)
    )

    val cityList = MutableLiveData<List<City>>()
    val cityClickEvent = SingleLiveEvent<City>()
    val cityAddEvent = SingleLiveEvent<City>()

    init {
        cityList.value = cityListSource
    }

    fun cityClicked(city: City) {
        cityClickEvent(city)
    }

    fun cityAdded(city:City)
    {
        cityListSource.add(city)
        cityList.value = cityListSource
        cityAddEvent(city)
    }
}