package de.marxhendrik.skullandbones.magnetsearch.di

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.marxhendrik.skullandbones.magnetsearch.domain.MagnetSearchUseCase
import de.marxhendrik.skullandbones.magnetsearch.ui.MagnetSearchFragment
import de.marxhendrik.skullandbones.magnetsearch.ui.MagnetSearchViewModel

@Module(includes = [ViewModelFactoryModule::class])
object MagnetSearchModule {

    @Provides
    @JvmStatic
    fun magnetSearchViewModel(
        factory: ViewModelProvider.Factory,
        fragment: MagnetSearchFragment
    ) =
        fragment.viewModels<MagnetSearchViewModel>(factoryProducer = { factory })

    @Provides
    @JvmStatic
    fun magnetSearchUseCase() = MagnetSearchUseCase()

}
