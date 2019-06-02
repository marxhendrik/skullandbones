package de.marxhendrik.skullandbones.core.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder<T>(
    private val binding: ViewDataBinding,
    private val bindingItemId: Int
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T, clickFunc: (T) -> Unit) {
        binding.setVariable(bindingItemId, item)
        binding.executePendingBindings()
        binding.root.setOnClickListener { clickFunc(item) }
    }
}
