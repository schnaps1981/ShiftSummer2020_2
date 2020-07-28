package com.shiftsummer2020_2.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.shiftsummer2020_2.R
import com.shiftsummer2020_2.app.Constants
import com.shiftsummer2020_2.data.appmodel.City
import com.shiftsummer2020_2.presentation.ui.adapters.CityListAdapter
import com.shiftsummer2020_2.presentation.ui.base.BaseAdapterCallback
import com.shiftsummer2020_2.presentation.viewmodels.CityListViewModel
import kotlinx.android.synthetic.main.fragment_city_list.*


class CityListFragment : Fragment(R.layout.fragment_city_list) {


    private val viewModel: CityListViewModel by viewModels()

    private val cityAdapter = CityListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rvCitiesList.layoutManager = LinearLayoutManager(activity?.applicationContext)
        rvCitiesList.adapter = cityAdapter

        viewModel.cityList.observe(viewLifecycleOwner, Observer(::setCityList))
        viewModel.cityClickEvent.observe(viewLifecycleOwner, Observer {
            showWeatherDetails(it, view)
        })


        cityAdapter.attachCallback(object : BaseAdapterCallback<City> {
            override fun onItemClick(model: City, view: View) {
                viewModel.cityClicked(model)
            }
        })

        fabAddCity.setOnClickListener {
            viewModel.cityAdded(City("Rostov" to null))
        }
    }

    private fun setCityList(cityList: List<City>) {
        cityAdapter.setListDiff(cityList)
    }

    private fun showWeatherDetails(model: City, view: View) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.KEY_WEATHER_DETAILS, model)

        Navigation
            .findNavController(view)
            .navigate(R.id.action_cityListFragment_to_detailsFragment, bundle)
    }


    companion object {
        fun newInstance(args: Bundle? = null): CityListFragment {
            return CityListFragment().also { it.arguments = args }
        }
    }
}