package com.shiftsummer2020_2.screens.details.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.shiftsummer2020_2.R
import com.shiftsummer2020_2.app.Constants
import com.shiftsummer2020_2.screens.main.domain.entities.City
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var details: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            details = it.getParcelable(Constants.KEY_WEATHER_DETAILS)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCityDetail.text = details?.city?.first
    }

    companion object {
        fun newInstance(args: Bundle? = null): DetailsFragment {
            return DetailsFragment()
                .also { it.arguments = args }
        }
    }
}