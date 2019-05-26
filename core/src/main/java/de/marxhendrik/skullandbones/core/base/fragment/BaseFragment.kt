package de.marxhendrik.skullandbones.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

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

    open fun onViewCreated() {}

    fun <T> LiveData<T>.observe(func: (T) -> Unit) = observe(this@BaseFragment, Observer<T> { t -> func(t) })
}
