package de.marxhendrik.skullandbones.search.di

import dagger.Module
import dagger.Provides
import de.marxhendrik.skullandbones.search.data.api.BayMagnetSearchApi
import de.marxhendrik.skullandbones.search.data.api.JsoupApiBridge
import de.marxhendrik.skullandbones.search.data.api.MagnetSearchApi
import de.marxhendrik.skullandbones.search.data.repo.MagnetSearchRepo
import de.marxhendrik.skullandbones.search.domain.SearchMagnetLinkUsecase

@Module
abstract class MagnetSearchModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun magnetSearchUseCase(repo: MagnetSearchRepo) =
            SearchMagnetLinkUsecase(
                repo
            )

        @Provides
        @JvmStatic
        fun searchApi(jsoup: JsoupApiBridge): MagnetSearchApi =
            BayMagnetSearchApi(jsoup)
    }

}
