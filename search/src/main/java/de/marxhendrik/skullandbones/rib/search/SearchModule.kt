package de.marxhendrik.skullandbones.rib.search

import com.badoo.ribs.core.Node
import dagger.Module
import dagger.Provides
import de.marxhendrik.skullandbones.rib.search.data.api.BayMagnetSearchApi
import de.marxhendrik.skullandbones.rib.search.data.api.JsoupApiBridge
import de.marxhendrik.skullandbones.rib.search.data.api.MagnetSearchApi
import de.marxhendrik.skullandbones.rib.search.data.repo.MagnetSearchRepo
import de.marxhendrik.skullandbones.rib.search.domain.SearchMagnetLinkUsecase

@Module
abstract class SearchModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun magnetSearchUseCase(repo: MagnetSearchRepo) = SearchMagnetLinkUsecase(repo)

        @Provides
        @JvmStatic
        fun searchApi(jsoup: JsoupApiBridge): MagnetSearchApi = BayMagnetSearchApi(jsoup)

        @Provides
        @JvmStatic
        fun node(): Node<Nothing> {
            TODO()
        }
    }

}
