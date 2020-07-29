package com.shiftsummer2020_2.screens.main.presentation.ui.viewholders

import android.view.View
import com.shiftsummer2020_2.screens.main.domain.entities.City
import com.shiftsummer2020_2.global.baseadapter.BaseViewHolder
import kotlinx.android.synthetic.main.city_list_item.view.*

class CityViewHolder(itemView: View) :
    BaseViewHolder<City>(itemView = itemView) {
    override fun bind(model: City) {
        itemView.tvCity.text = model.city.first
    }
}