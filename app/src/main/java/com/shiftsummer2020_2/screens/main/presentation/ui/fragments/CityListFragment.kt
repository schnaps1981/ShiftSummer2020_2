package com.shiftsummer2020_2.screens.main.presentation.ui.fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
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
import com.shiftsummer2020_2.global.viewmodel.ProgressStatus
import com.shiftsummer2020_2.screens.main.di.CityListViewModelFactory
import com.shiftsummer2020_2.screens.main.presentation.ui.adapters.CityListAdapter
import com.shiftsummer2020_2.screens.main.presentation.viewmodels.CityListViewModel
import exapmle.com.common.City
import exapmle.com.common.CityDto
import exapmle.com.common.toCityDto
import kotlinx.android.synthetic.main.fragment_city_list.*
import timber.log.Timber


class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private val viewModel: CityListViewModel by viewModels { CityListViewModelFactory() }

    private val cityAdapter = CityListAdapter(
        onClickListener = { city -> viewModel.cityClicked(city = city) },
        onLongClickListener = { city, view -> showMenu(city, view) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvCitiesList.layoutManager = LinearLayoutManager(activity?.applicationContext)
        rvCitiesList.adapter = cityAdapter

        viewModel.cityList.observe(viewLifecycleOwner, Observer { cityListChanged(it) })
        viewModel.messagesSnackbar.observe(viewLifecycleOwner, Observer { showSnackbar(it) })
        viewModel.cityItemClickEvent.observe(
            viewLifecycleOwner,
            Observer { showWeatherDetails(it, view) })
        viewModel.progressState.observe(viewLifecycleOwner, Observer { progressChanged(it) })

        //val addDialog = prepareAddDialog(viewModel)
        val addDialog = prepareDialog(null) { viewModel.addItem(it) }

        fabAddCity.setOnClickListener {
            addDialog.show()
        }
    }

    private fun showMenu(city: City, anchor: View): Boolean {
        val popupMenu = context?.let { PopupMenu(it, anchor) }
        popupMenu?.inflate(R.menu.menu)
        popupMenu?.show()
        popupMenu?.setOnMenuItemClickListener { menuItem ->
            popupMenuListener(menuItem = menuItem, city = city)
        }
        return false
    }

    private fun cityListChanged(list: List<City>?) {
        list?.let { setCityList(it) }
    }

    private fun progressChanged(it: ProgressStatus?) {
        when (it) {
            is ProgressStatus.Fail, ProgressStatus.Done -> {
                fabAddCity.visibility = View.VISIBLE
                pbLoadCities.visibility = View.GONE
            }
            is ProgressStatus.InProgress -> {
                fabAddCity.visibility = View.GONE
                pbLoadCities.visibility = View.VISIBLE
            }
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
        this.view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
    }

    private fun popupMenuListener(menuItem: MenuItem, city: City): Boolean {
        when (menuItem.itemId) {
            R.id.menu_edit -> {
                val editDialog = prepareDialog(city.toCityDto()) { viewModel.updateItem(it, city.id)}
                editDialog.show()
            }
            R.id.menu_delete -> {
                viewModel.deleteItem(city)
            }
            else -> Timber.d("Wrong menu item Id")
        }
        return false
    }

    private fun <T> prepareDialog(initValues: CityDto?, action: (city: CityDto) -> T): AlertDialog {
        val dialog = MaterialAlertDialogBuilder(context)
        val view = layoutInflater.inflate(R.layout.add_city, null)

        val city = view.findViewById(R.id.tvAddCityCity) as TextInputEditText
        val temperature = view.findViewById(R.id.tvAddCityTemperature) as TextInputEditText

        initValues?.let {
            city.text?.append(it.city)
            temperature.text?.append(it.temperature.toString())
        }

        dialog.setView(view)
        dialog.setPositiveButton("OK") { _: DialogInterface, _: Int ->
            action.invoke(
                CityDto(
                    city = city.text.toString(),
                    temperature = temperature.text.toString().toInt()
                )
            )
        }
        dialog.setNegativeButton("Cancel") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        dialog.setOnCancelListener { it.dismiss() }

        return dialog.create()
    }


    companion object {
        fun newInstance(args: Bundle? = null): CityListFragment {
            return CityListFragment()
                .also { it.arguments = args }
        }
    }
}

