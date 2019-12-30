package de.marxhendrik.skullandbones.magnetsearch.di

import dagger.Module
import dagger.Provides
import de.marxhendrik.skullandbones.core.base.viewmodel.ViewModelFactory
import de.marxhendrik.skullandbones.magnetsearch.data.api.BayMagnetSearchApi
import de.marxhendrik.skullandbones.magnetsearch.data.api.JsoupApiBridge
import de.marxhendrik.skullandbones.magnetsearch.data.api.MagnetSearchApi
import de.marxhendrik.skullandbones.magnetsearch.data.repo.MagnetSearchRepo
import de.marxhendrik.skullandbones.magnetsearch.domain.SearchMagnetLinkUsecase
import de.marxhendrik.skullandbones.magnetsearch.ui.model.MagnetSearchViewModel
import de.marxhendrik.skullandbones.magnetsearch.ui.view.MagnetSearchFragment

@Module(includes = [ViewModelFactoryModule::class])
abstract class MagnetSearchModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun magnetSearchViewModel(
            factory: ViewModelFactory,
            fragment: MagnetSearchFragment
        ): MagnetSearchViewModel = factory.get(fragment)

        @Provides
        @JvmStatic
        fun magnetSearchUseCase(repo: MagnetSearchRepo) = SearchMagnetLinkUsecase(repo)

        @Provides
        @JvmStatic
        fun searchApi(jsoup: JsoupApiBridge): MagnetSearchApi = BayMagnetSearchApi(jsoup)
    }

}
