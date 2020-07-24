package com.shiftsummer2020_2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.shiftsummer2020_2.R
import com.shiftsummer2020_2.data.apimodel.Data
import com.shiftsummer2020_2.data.appmodel.City
import com.shiftsummer2020_2.ui.base.BaseAdapter
import com.shiftsummer2020_2.ui.base.BaseViewHolder
import com.shiftsummer2020_2.ui.viewholders.CityViewHolder

class CityListAdapter : BaseAdapter<City>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<City> {
        return CityViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.city_list_item, parent, false), parent
        )
    }
}