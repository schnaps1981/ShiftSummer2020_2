package com.shiftsummer2020_2.screens.main.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiftsummer2020_2.global.viewmodel.SingleLiveEvent
import com.shiftsummer2020_2.screens.main.domain.AddCityUseCase
import com.shiftsummer2020_2.screens.main.domain.DeleteCityUseCase
import com.shiftsummer2020_2.screens.main.domain.GetCitiesListUseCase
import com.shiftsummer2020_2.screens.main.domain.UpdateCityUseCase
import com.shiftsummer2020_2.screens.main.domain.apiwrapper.ApiResultWrapper
import exapmle.com.common.City
import exapmle.com.common.CityDto
import kotlinx.coroutines.launch

class CityListViewModel(
    private val getCitiesListUseCase: GetCitiesListUseCase,
    private val addCityUseCase: AddCityUseCase,
    private val deleteCityUseCase: DeleteCityUseCase,
    private val updateCityUseCase: UpdateCityUseCase
) : ViewModel() {

    val cityList = MutableLiveData<List<City>?>()
    val errors = MutableLiveData<String>()

    val cityClickEvent = SingleLiveEvent<City>()
    val cityAddEvent = SingleLiveEvent<City>()

    init {
        viewModelScope.launch {
            when (val getCitiesListUseCaseResult = getCitiesListUseCase())
            {
                is ApiResultWrapper.NetworkError -> errors.value = "Network Error"
                is ApiResultWrapper.ApiError -> errors.value = getCitiesListUseCaseResult.code?.toString()
                is ApiResultWrapper.Success -> cityList.value = getCitiesListUseCaseResult.result
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

    fun cityClickedLong(city: City): Boolean {
        return false
    }

    fun addCity(city: String, temperature: Int) {
        viewModelScope.launch {
            addCityUseCase.invoke(CityDto(city = city, temperature = temperature))
        }
    }
}