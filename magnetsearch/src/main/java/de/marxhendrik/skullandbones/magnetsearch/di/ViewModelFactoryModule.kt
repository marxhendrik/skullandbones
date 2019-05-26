package de.marxhendrik.skullandbones.magnetsearch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import de.marxhendrik.skullandbones.core.base.executor.CoroutineExecutor
import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.core.base.viewmodel.ViewModelFactory
import de.marxhendrik.skullandbones.core.base.viewmodel.ViewModelKey
import de.marxhendrik.skullandbones.core.base.viewmodel.ViewModelMap
import de.marxhendrik.skullandbones.core.di.scope.FeatureScope
import de.marxhendrik.skullandbones.magnetsearch.ui.model.MagnetSearchViewModel

@Module
object ViewModelFactoryModule {

    @Provides
    @JvmStatic
    @FeatureScope
    fun viewModelFactory(providers: ViewModelMap): ViewModelProvider.Factory = ViewModelFactory(providers)

    @Provides
    @IntoMap
    @JvmStatic
    @ViewModelKey(MagnetSearchViewModel::class)
    fun viewModelIntoMap(executor: Executor): ViewModel =
        MagnetSearchViewModel(executor)

    @Provides
    @JvmStatic
    fun executor(): Executor = CoroutineExecutor()

}
