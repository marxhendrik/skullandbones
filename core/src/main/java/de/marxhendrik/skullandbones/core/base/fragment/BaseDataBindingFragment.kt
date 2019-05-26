package de.marxhendrik.skullandbones.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDataBindingFragment<T : ViewDataBinding> : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return DataBindingUtil.inflate<T>(inflater, layoutId, container, false).let {
            it.setLifecycleOwner { lifecycle }
            bind(it)
            it.root
        }
    }

    abstract fun bind(binding: T)
}
