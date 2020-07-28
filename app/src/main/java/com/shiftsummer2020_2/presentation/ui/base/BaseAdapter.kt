package com.shiftsummer2020_2.presentation.ui.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<P> : RecyclerView.Adapter<BaseViewHolder<P>>() {
    protected var mDataList: MutableList<P> = ArrayList()
    private var mCallback: BaseAdapterCallback<P>? = null

    private var mDiffCallback: DiffCallback<P>? = null

    fun attachCallback(callback: BaseAdapterCallback<P>) {
        this.mCallback = callback
    }

    fun detachCallback() {
        this.mCallback = null
    }

    fun setDiffCallBack(callback: DiffCallback<P>)
    {
        this.mDiffCallback = callback
    }

    @Deprecated ("Use setListDiff method")
    fun setList(dataList: List<P>) {
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setListDiff(mNewList: List<P>) {
        mDiffCallback?.let {
            it.setLists(mDataList, mNewList)
            val diffResult = DiffUtil.calculateDiff(it)
            diffResult.dispatchUpdatesTo(this)
        }
        mDataList.clear()
        mDataList.addAll(mNewList)
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

    override fun getItemCount(): Int {
        return mDataList.count()
    }

    abstract class DiffCallback<P>: DiffUtil.Callback() {

        private val mOldItems = arrayListOf<P>()
        private val mNewItems = arrayListOf<P>()

        fun setLists(oldItems: List<P>, newItems: List<P>)
        {
            mOldItems.clear()
            mOldItems.addAll(oldItems)

            mNewItems.clear()
            mNewItems.addAll(newItems)
        }
        override fun getOldListSize() = mOldItems.size
        override fun getNewListSize() = mNewItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            itemsTheSame(mOldItems[oldItemPosition], mNewItems[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            contentsTheSame(mOldItems[oldItemPosition], mNewItems[newItemPosition])

        abstract fun itemsTheSame(oldItem: P, newItem: P): Boolean
        abstract fun contentsTheSame(oldItem: P, newItem: P): Boolean
    }
}
