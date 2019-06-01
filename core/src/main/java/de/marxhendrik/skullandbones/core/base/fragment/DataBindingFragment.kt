package de.marxhendrik.skullandbones.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class DataBindingFragment<I> : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, container, false).let {
            it.setLifecycleOwner { lifecycle }
            bind(it)
            it.root
        }
    }

    protected open fun bind(binding: ViewDataBinding) {
        val bindingData = getBindingData()
        binding.setVariable(bindingData.id, bindingData.Item)
    }

    abstract fun getBindingData(): BindingData<I>

    data class BindingData<T>(val id: Int, val Item: T)
}
