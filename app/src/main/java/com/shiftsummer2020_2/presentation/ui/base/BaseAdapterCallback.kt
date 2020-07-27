package com.shiftsummer2020_2.presentation.ui.base

import android.view.View

interface BaseAdapterCallback<T> {
    fun onItemClick(model: T, view: View)
    fun onLongClick(model: T, view: View) = true
}