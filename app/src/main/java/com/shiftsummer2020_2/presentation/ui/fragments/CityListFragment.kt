package com.shiftsummer2020_2.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
        super.onViewCreated(view, savedInstanceState)

        rvCitiesList.layoutManager = LinearLayoutManager(activity?.applicationContext)
        rvCitiesList.adapter = cityAdapter

        viewModel.cityList.observe(viewLifecycleOwner, Observer(::setCityList))
        viewModel.cityClickEvent.observe(viewLifecycleOwner, Observer(::showWeatherDetails))


        cityAdapter.attachCallback(object : BaseAdapterCallback<City>{
            override fun onItemClick(model: City, view: View) {
                viewModel.cityClicked(model)
            }
        })
    }

    private fun setCityList(cityList : List<City>)
    {
        cityAdapter.updateItems(cityList)
    }

    private fun showWeatherDetails(model: City)
    {
        val bundle = Bundle()
        bundle.putParcelable(Constants.KEY_WEATHER_DETAILS, model)
        val detailsFragment = DetailsFragment.getInstance(args = bundle)
        parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.animator.slide_in_top, R.animator.slide_out_right)
            .replace(R.id.frame_main_activity, detailsFragment)
            .addToBackStack(null)
            .commit()

    }


    companion object {
        fun getInstance(args: Bundle? = null): CityListFragment {
            return CityListFragment().also { it.arguments = args }
        }
    }
}