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

    fun deleteItem(city: City) {
        viewModelScope.launch {
            perfomAction(deleteCityUseCase(city.id), "Delete success")
            updateList()
        }
    }

    fun updateItem(city: CityDto, id: Long) {
        viewModelScope.launch {
            perfomAction(updateCityUseCase(city, id), "City updated")
            updateList()
        }
    }

    fun addItem(city: CityDto) {
        viewModelScope.launch {
            perfomAction(addCityUseCase(city), "City Added")
            updateList()
        }

    }

    private fun updateList() {
        viewModelScope.launch {
            perfomAction(getCitiesListUseCase())
        }
    }

    private fun <T> perfomAction(useCase: ApiResultWrapper<T>, message: String? = null) {
        progressState.value = ProgressStatus.InProgress
        when (useCase) {
            is ApiResultWrapper.NetworkError -> {
                messagesSnackbar.value = "Network Error"
                progressState.value = ProgressStatus.Fail
            }
            is ApiResultWrapper.ApiError -> {
                messagesSnackbar.value = "API error. Code ${useCase.code?.toString()}"
                progressState.value = ProgressStatus.Fail
            }
            is ApiResultWrapper.OtherError -> {
                messagesSnackbar.value = useCase.message
                progressState.value = ProgressStatus.Fail
            }
            is ApiResultWrapper.Success -> {
                progressState.value = ProgressStatus.Done
                message?.let { messagesSnackbar.value = it }
                if (useCase.result.isNotEmptyCityList())
                    cityList.value = useCase.result as List<City>
            }
        }
    }
}

private fun <T> T.isNotEmptyCityList() =
    try {
        this as List<City>
        true
    } catch (e: Exception) {
        false
    }



