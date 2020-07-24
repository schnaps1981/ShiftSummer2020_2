package com.shiftsummer2020_2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.shiftsummer2020_2.R
import com.shiftsummer2020_2.app.Constants
import com.shiftsummer2020_2.data.appmodel.City
import kotlinx.android.synthetic.main.fragment_details.*
import timber.log.Timber

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
        fun getInstance(args: Bundle? = null): DetailsFragment {
            return DetailsFragment().also { it.arguments = args }
        }
    }
}