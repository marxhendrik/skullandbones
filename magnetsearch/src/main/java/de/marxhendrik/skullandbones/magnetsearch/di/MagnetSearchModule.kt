package de.marxhendrik.skullandbones.magnetsearch.di

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import de.marxhendrik.skullandbones.magnetsearch.data.api.BayMagnetSearchApi
import de.marxhendrik.skullandbones.magnetsearch.data.api.JsoupApiBridge
import de.marxhendrik.skullandbones.magnetsearch.data.api.MagnetSearchApi
import de.marxhendrik.skullandbones.magnetsearch.data.repo.MagnetSearchRepo
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
    ): MagnetSearchViewModel = fragment.viewModels<MagnetSearchViewModel>(factoryProducer = { factory }).value

    @Provides
    @JvmStatic
    fun magnetSearchUseCase(repo: MagnetSearchRepo) = MagnetSearchUseCase(repo)

    @Provides
    @JvmStatic
    fun searchApi(jsoup: JsoupApiBridge): MagnetSearchApi = BayMagnetSearchApi(jsoup)

}
