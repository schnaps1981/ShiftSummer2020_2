package com.shiftsummer2020_2.screens.main.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiftsummer2020_2.global.viewmodel.ProgressStatus
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

    val messagesSnackbar = SingleLiveEvent<String>()
    val cityItemClickEvent = SingleLiveEvent<City>()
    val progressState = SingleLiveEvent<ProgressStatus>()


    init {
        updateList()
    }


    fun cityClicked(city: City) {
        cityItemClickEvent(city)
    }

    fun deleteItem(city: City)
    {
        viewModelScope.launch {
            progressState.value = ProgressStatus.InProgress
            when (val deleteCityUseCaseResult = deleteCityUseCase(city.id)) {
                is ApiResultWrapper.NetworkError -> {
                    messagesSnackbar.value = "Network Error"
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.ApiError -> {
                    messagesSnackbar.value = deleteCityUseCaseResult.code?.toString()
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.OtherError -> {
                    messagesSnackbar.value = deleteCityUseCaseResult.message
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.Success -> {
                    updateList()
                    progressState.value = ProgressStatus.Done
                    messagesSnackbar.value = "Delete success"
                }
            }
        }
    }

    fun updateItem(city: CityDto, id: Long)
    {
        viewModelScope.launch {
            progressState.value = ProgressStatus.InProgress
            when (val updateCityUseCaseResult = updateCityUseCase(city, id)) {
                is ApiResultWrapper.NetworkError -> {
                    messagesSnackbar.value = "Network Error"
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.ApiError -> {
                    messagesSnackbar.value = updateCityUseCaseResult.code?.toString()
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.OtherError -> {
                    messagesSnackbar.value = updateCityUseCaseResult.message
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.Success -> {
                    updateList()
                    progressState.value = ProgressStatus.Done
                }
            }
        }
    }

    fun addItem(city: CityDto) {
        viewModelScope.launch {
            progressState.value = ProgressStatus.InProgress
            when (val addCityUseCaseResult = addCityUseCase(city)) {
                is ApiResultWrapper.NetworkError -> {
                    messagesSnackbar.value = "Network Error"
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.ApiError -> {
                    messagesSnackbar.value = addCityUseCaseResult.code?.toString()
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.OtherError -> {
                    messagesSnackbar.value = addCityUseCaseResult.message
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.Success -> {
                    updateList()
                    progressState.value = ProgressStatus.Done
                    messagesSnackbar.value = "City Added"
                }
            }
        }
    }

    private fun updateList() {
        viewModelScope.launch {
            progressState.value = ProgressStatus.InProgress
            when (val getCitiesListUseCaseResult = getCitiesListUseCase()) {
                is ApiResultWrapper.NetworkError -> {
                    messagesSnackbar.value = "Network Error"
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.ApiError -> {
                    messagesSnackbar.value = getCitiesListUseCaseResult.code?.toString()
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.OtherError -> {
                    messagesSnackbar.value = getCitiesListUseCaseResult.message
                    progressState.value = ProgressStatus.Fail
                }
                is ApiResultWrapper.Success -> {
                    cityList.value = getCitiesListUseCaseResult.result
                    progressState.value = ProgressStatus.Done
                }
            }
        }
    }


}