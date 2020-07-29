package com.shiftsummer2020_2.screens.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shiftsummer2020_2.screens.main.data.CitiesDataSourceImpl
import com.shiftsummer2020_2.screens.main.data.CitiesRepositoryImpl
import com.shiftsummer2020_2.screens.main.domain.GetCitiesListUseCase
import com.shiftsummer2020_2.screens.main.presentation.viewmodels.CityListViewModel

class CityListViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == CityListViewModel::class.java)
        {
            val citiesDataSource =
                CitiesDataSourceImpl()
            val citiesRepository =
                CitiesRepositoryImpl(
                    citiesDataSource = citiesDataSource
                )
            val getCitiesListUseCase =
                GetCitiesListUseCase(
                    citiesRepository
                )

            return CityListViewModel(
                getCitiesListUseCase
            ) as T
        }
        else
        {
            error("Unexpected class $modelClass")
        }
    }

}