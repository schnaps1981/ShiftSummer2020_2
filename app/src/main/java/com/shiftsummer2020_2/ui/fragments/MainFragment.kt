package com.shiftsummer2020_2.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.shiftsummer2020_2.R
import com.shiftsummer2020_2.app.Constants
import com.shiftsummer2020_2.data.appmodel.City
import com.shiftsummer2020_2.ui.adapters.CityListAdapter
import com.shiftsummer2020_2.ui.base.BaseAdapterCallback
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(R.layout.fragment_main) {

    private val cityList = arrayListOf(
        City("Tomsk" to null),
        City("Novosibirsk" to null),
        City("Rostov-on-Don" to null),
        City("Krasnodar" to null),
        City("Kaliningrad" to null),
        City("Khabarovsk" to null),
        City("Vladivostok" to null)
    )

    private val cityAdapter = CityListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCitiesList.layoutManager = LinearLayoutManager(activity?.applicationContext)
        rvCitiesList.adapter = cityAdapter

        cityAdapter.updateItems(cityList)

        cityAdapter.attachCallback(object : BaseAdapterCallback<City>{
            override fun onItemClick(model: City, view: View) {
                val bundle = Bundle()
                bundle.putParcelable(Constants.KEY_WEATHER_DETAILS, model)
                val detailsFragment = DetailsFragment.getInstance(args = bundle)
                parentFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right)
                    .replace(R.id.frame_main_activity, detailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
    }


    companion object {
        fun getInstance(args: Bundle? = null): MainFragment {
            return MainFragment().also { it.arguments = args }
        }
    }
}