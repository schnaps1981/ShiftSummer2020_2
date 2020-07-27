package com.shiftsummer2020_2.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.shiftsummer2020_2.R
import com.shiftsummer2020_2.data.appmodel.City
import com.shiftsummer2020_2.presentation.ui.base.BaseAdapter
import com.shiftsummer2020_2.presentation.ui.base.BaseViewHolder
import com.shiftsummer2020_2.presentation.ui.viewholders.CityViewHolder

class CityListAdapter : BaseAdapter<City>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<City> {
        return CityViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.city_list_item, parent, false), parent
        )
    }
}