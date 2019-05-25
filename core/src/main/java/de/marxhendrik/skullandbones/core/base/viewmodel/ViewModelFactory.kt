package de.marxhendrik.skullandbones.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val providers: ViewModelMap
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return requireNotNull(providers[modelClass as Class<out ViewModel>]).get() as T
    }
}

typealias ViewModelMap = Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

