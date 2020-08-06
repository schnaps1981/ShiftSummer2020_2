package com.shiftsummer2020_2.screens.details.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.shiftsummer2020_2.R
import com.shiftsummer2020_2.app.Constants
import exapmle.com.common.City
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var details: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            details = it.getSerializable(Constants.KEY_WEATHER_DETAILS) as City?
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCityDetail.text = details?.city
        tvTemperatureDetail.text = details?.temperature.toString()
    }

    companion object {
        fun newInstance(args: Bundle? = null): DetailsFragment {
            return DetailsFragment()
                .also { it.arguments = args }
        }
    }
}