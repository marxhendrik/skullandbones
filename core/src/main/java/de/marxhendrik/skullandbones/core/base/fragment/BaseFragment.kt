package de.marxhendrik.skullandbones.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import de.marxhendrik.skullandbones.core.base.livedata.observe

abstract class BaseFragment : Fragment() {

    abstract val layoutId: Int

    protected var contentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        contentView = view
        onViewCreated()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    open fun onViewCreated() {}

    open fun inject() {}

    fun <T> LiveData<T>.observe(func: (T) -> Unit) = observe(this@BaseFragment, func)
}

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
