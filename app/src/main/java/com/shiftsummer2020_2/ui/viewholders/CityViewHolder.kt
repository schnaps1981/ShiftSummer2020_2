package com.shiftsummer2020_2.ui.viewholders

import android.view.View
import android.view.ViewParent
import com.shiftsummer2020_2.data.apimodel.Data
import com.shiftsummer2020_2.data.appmodel.City
import com.shiftsummer2020_2.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.city_list_item.view.*

class CityViewHolder(itemView: View, private val parent: ViewParent) :
    BaseViewHolder<City>(itemView = itemView) {
    override fun bind(model: City) {
        itemView.tvCity.text = model.city.first
    }
}