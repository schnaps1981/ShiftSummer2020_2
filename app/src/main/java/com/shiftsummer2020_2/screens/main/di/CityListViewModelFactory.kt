package com.shiftsummer2020_2.screens.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shiftsummer2020_2.screens.main.data.CitiesApi
import com.shiftsummer2020_2.screens.main.data.CitiesNetworkDataSourceImpl
import com.shiftsummer2020_2.screens.main.data.CitiesRepositoryImpl
import com.shiftsummer2020_2.screens.main.domain.AddCityUseCase
import com.shiftsummer2020_2.screens.main.domain.DeleteCityUseCase
import com.shiftsummer2020_2.screens.main.domain.GetCitiesListUseCase
import com.shiftsummer2020_2.screens.main.domain.UpdateCityUseCase
import com.shiftsummer2020_2.screens.main.presentation.viewmodels.CityListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == CityListViewModel::class.java) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://shiftsummer2020.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            val api = retrofit.create(CitiesApi::class.java)

            val citiesDataSource = CitiesNetworkDataSourceImpl(api = api)
            val citiesRepository = CitiesRepositoryImpl(citiesNetworkDataSource = citiesDataSource)
            val getCitiesListUseCase = GetCitiesListUseCase(citiesRepository = citiesRepository)
            val addCityUseCase = AddCityUseCase(citiesRepository = citiesRepository)
            val deleteCityUseCase = DeleteCityUseCase(citiesRepository = citiesRepository)
            val updateCityUseCase = UpdateCityUseCase(citiesRepository = citiesRepository)


            return CityListViewModel(
                getCitiesListUseCase,
                addCityUseCase,
                deleteCityUseCase,
                updateCityUseCase
            ) as T
        } else {
            error("Unexpected class $modelClass")
        }
    }

}