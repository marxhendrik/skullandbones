package de.marxhendrik.skullandbones.core.base.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val providers: ViewModelMap
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return requireNotNull(providers[modelClass as Class<out ViewModel>]).get() as T
    }

    inline fun <reified T : ViewModel> get(fragment: Fragment): T =
        ViewModelProviders.of(fragment, this@ViewModelFactory)[T::class.java]
}


typealias ViewModelMap = Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

