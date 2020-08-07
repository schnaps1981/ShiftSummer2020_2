package com.shiftsummer2020_2.screens.main.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shiftsummer2020_2.R
import com.shiftsummer2020_2.global.baseadapter.BaseAdapter
import com.shiftsummer2020_2.global.baseadapter.BaseViewHolder
import com.shiftsummer2020_2.screens.main.presentation.ui.viewholders.CityViewHolder
import exapmle.com.common.City

class CityListAdapter(
    onClickListener: ((City) -> Unit)? = null,
    onLongClickListener: ((City, View) -> Boolean)? = null
) : BaseAdapter<City>(onClickListener, onLongClickListener) {

    init {
        this.setDiffCallBack(object : DiffCallback<City>() {
            override fun areItemsTheSame(oldItem: City, newItem: City) =
                oldItem.city == newItem.city

            override fun areContentsTheSame(oldItem: City, newItem: City) =
                oldItem == newItem
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<City> {
        return CityViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.city_list_item, parent, false)
        )
    }
}