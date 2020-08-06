package com.shiftsummer2020_2.screens.main.presentation.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.shiftsummer2020_2.R
import com.shiftsummer2020_2.app.Constants
import com.shiftsummer2020_2.screens.main.di.CityListViewModelFactory
import com.shiftsummer2020_2.screens.main.presentation.ui.adapters.CityListAdapter
import com.shiftsummer2020_2.screens.main.presentation.viewmodels.CityListViewModel
import exapmle.com.common.City
import kotlinx.android.synthetic.main.fragment_city_list.*
import kotlin.random.Random


class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private val viewModel: CityListViewModel by viewModels { CityListViewModelFactory() }

    private val cityAdapter = CityListAdapter(
        onClickListener = { city -> viewModel.cityClicked(city = city) },
        onLongClickListener = { city -> viewModel.cityClickedLong(city = city) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rvCitiesList.layoutManager = LinearLayoutManager(activity?.applicationContext)
        rvCitiesList.adapter = cityAdapter

        viewModel.cityList.observe(viewLifecycleOwner, Observer {
            it?.let { list ->
                setCityList(list)
            }
        })

        viewModel.errors.observe(viewLifecycleOwner, Observer {
            showSnackbar(it)
        })

        viewModel.cityClickEvent.observe(viewLifecycleOwner, Observer {
            showWeatherDetails(it, view)
        })

        val addDialog = prepareAddDialog(viewModel)

        fabAddCity.setOnClickListener {
          addDialog.show()
        }
    }

    private fun setCityList(cityList: List<City>) {
        cityAdapter.setListDiff(cityList)
    }

    private fun showWeatherDetails(model: City, view: View) {
        val bundle = Bundle()
        bundle.putSerializable(Constants.KEY_WEATHER_DETAILS, model)

        Navigation
            .findNavController(view)
            .navigate(R.id.action_cityListFragment_to_detailsFragment, bundle)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(rvCitiesList, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun prepareAddDialog(viewModel: CityListViewModel): AlertDialog {
        val dialog = MaterialAlertDialogBuilder(context)
        val view = layoutInflater.inflate(R.layout.add_city, null)
        dialog.setView(view)
        dialog.setPositiveButton("OK") { _: DialogInterface, _: Int ->
            val city = view.findViewById(R.id.tvAddCityCity) as TextInputEditText
            val temperature = view.findViewById(R.id.tvAddCityTemperature) as TextInputEditText
            viewModel.addCity(city.text.toString(), temperature.text.toString().toInt())
        }
        dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->  }
        return dialog.create()
    }


    companion object {
        fun newInstance(args: Bundle? = null): CityListFragment {
            return CityListFragment()
                .also { it.arguments = args }
        }
    }
}