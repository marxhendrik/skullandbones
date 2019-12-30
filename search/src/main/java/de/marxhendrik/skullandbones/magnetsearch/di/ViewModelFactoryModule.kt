package de.marxhendrik.skullandbones.magnetsearch.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import de.marxhendrik.skullandbones.core.base.executor.CoroutineExecutor
import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.core.base.viewmodel.ViewModelFactory
import de.marxhendrik.skullandbones.core.base.viewmodel.ViewModelKey
import de.marxhendrik.skullandbones.core.base.viewmodel.ViewModelMap
import de.marxhendrik.skullandbones.core.di.scope.FeatureScope
import de.marxhendrik.skullandbones.magnetsearch.domain.SearchMagnetLinkUsecase
import de.marxhendrik.skullandbones.magnetsearch.ui.model.MagnetSearchViewModel
import de.marxhendrik.skullandbones.magnetsearch.ui.presentation.MagnetSearchUiController

@Module
object ViewModelFactoryModule {

    @Provides
    @JvmStatic
    @FeatureScope
    fun viewModelFactory(providers: ViewModelMap): ViewModelFactory = ViewModelFactory(providers)

    @Provides
    @IntoMap
    @JvmStatic
    @ViewModelKey(MagnetSearchViewModel::class)
    fun viewModelIntoMap(executor: Executor): ViewModel = MagnetSearchViewModel(executor)

    @Provides
    @JvmStatic
    fun executor(): Executor = CoroutineExecutor()

    @Provides
    @JvmStatic
    fun uiModel(
        viewmodel: MagnetSearchViewModel,
        usecase: SearchMagnetLinkUsecase
    ): MagnetSearchUiController = MagnetSearchUiController(
        viewmodel.uiModel,
        viewmodel,
        usecase
    )


}
