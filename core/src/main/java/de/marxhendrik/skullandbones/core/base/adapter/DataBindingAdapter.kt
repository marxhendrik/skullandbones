package de.marxhendrik.skullandbones.core.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DataBindingAdapter<T>(private val bindingItemId: Int) :
    RecyclerView.Adapter<DataBindingViewHolder<T>>() {
    protected val results = mutableListOf<T>()

    private var binding: ViewDataBinding? = null

    private fun makeBinding(
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
        LayoutInflater.from(parent.context),
        viewType,
        parent,
        false
    ).apply {
        binding = this
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) =
        holder.bind(getItemForPosition(position))

    override fun getItemCount(): Int = results.size

    open fun getItemForPosition(position: Int): T = results[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> =
        DataBindingViewHolder(
            makeBinding(
                parent,
                viewType
            ), bindingItemId
        )

    fun setData(data: List<T>) {
        results.clear()
        results.addAll(data)
        notifyChanged()
    }

    private fun notifyChanged() {
        binding?.notifyPropertyChanged(bindingItemId)
        notifyDataSetChanged()
    }
}
