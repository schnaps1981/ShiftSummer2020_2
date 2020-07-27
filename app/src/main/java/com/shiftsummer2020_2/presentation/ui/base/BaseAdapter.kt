package com.shiftsummer2020_2.presentation.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<P>: RecyclerView.Adapter<BaseViewHolder<P>>() {
    protected var mDataList: MutableList<P> = ArrayList()
    private var mCallback: BaseAdapterCallback<P>? = null

    fun attachCallback(callback: BaseAdapterCallback<P>) {
        this.mCallback = callback
    }

    fun detachCallback() {
        this.mCallback = null
    }

    fun setList(dataList: List<P>) {
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addItem(newItem: P) {
        mDataList.add(newItem)
        notifyItemInserted(mDataList.size - 1)
    }

    fun addItemToTop(newItem: P) {
        mDataList.add(0, newItem)
        notifyItemInserted(0)
    }

    fun updateItems(itemsList: List<P>) {
        mDataList.clear()
        setList(itemsList)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<P>, position: Int) {
        holder.bind(mDataList[position])

        holder.itemView.setOnClickListener {
            mCallback?.onItemClick(
                mDataList[position],
                holder.itemView
            )
        }
        holder.itemView.setOnLongClickListener {
            mCallback?.onLongClick(
                mDataList[position],
                holder.itemView
            ) ?: false
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<P> {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return mDataList.count()
    }
}
