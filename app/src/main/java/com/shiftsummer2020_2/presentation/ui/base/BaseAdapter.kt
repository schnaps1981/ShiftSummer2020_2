package com.shiftsummer2020_2.presentation.ui.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<P>(
    private val onClickListener: ((P) -> Unit)? = null,
    private val onLongClickListener: ((P) -> Boolean)? = null
) :
    RecyclerView.Adapter<BaseViewHolder<P>>() {
    protected var mDataList: MutableList<P> = ArrayList()
    private var mDiffCallback: DiffCallback<P>? = null

    fun setDiffCallBack(callback: DiffCallback<P>) {
        this.mDiffCallback = callback
    }

    @Deprecated("Use setListDiff method")
    fun setList(dataList: List<P>) {
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setListDiff(mNewList: List<P>) {
        mDiffCallback?.let {
            it.setLists(mDataList, mNewList)
            val diffResult = DiffUtil.calculateDiff(it)
            diffResult.dispatchUpdatesTo(this)
            mDataList.clear()
            mDataList.addAll(mNewList)
        }
            ?: throw NotImplementedError("You must set mDiffCallback as DiffUtil.Callback in child adapter")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<P>, position: Int) {
        holder.bind(mDataList[position])

        holder.itemView.setOnClickListener {
            onClickListener?.invoke(mDataList[position])
        }

        holder.itemView.setOnLongClickListener{
            onLongClickListener?.invoke(mDataList[position]) ?: false
        }
    }

    override fun getItemCount(): Int {
        return mDataList.count()
    }

    abstract class DiffCallback<P> : DiffUtil.Callback() {

        private val mOldItems = arrayListOf<P>()
        private val mNewItems = arrayListOf<P>()

        fun setLists(oldItems: List<P>, newItems: List<P>) {
            mOldItems.clear()
            mOldItems.addAll(oldItems)

            mNewItems.clear()
            mNewItems.addAll(newItems)
        }

        override fun getOldListSize() = mOldItems.size
        override fun getNewListSize() = mNewItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            areItemsTheSame(mOldItems[oldItemPosition], mNewItems[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            areContentsTheSame(mOldItems[oldItemPosition], mNewItems[newItemPosition])

        abstract fun areItemsTheSame(oldItem: P, newItem: P): Boolean
        abstract fun areContentsTheSame(oldItem: P, newItem: P): Boolean
    }
}
