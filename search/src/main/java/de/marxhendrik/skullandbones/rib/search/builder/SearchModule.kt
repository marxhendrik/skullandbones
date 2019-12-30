package de.marxhendrik.skullandbones.rib.search.builder

import com.badoo.ribs.core.Node
import dagger.Module
import dagger.Provides
import de.marxhendrik.skullandbones.rib.search.data.api.BayMagnetSearchApi
import de.marxhendrik.skullandbones.rib.search.data.api.JsoupApiBridge
import de.marxhendrik.skullandbones.rib.search.data.api.MagnetSearchApi
import de.marxhendrik.skullandbones.rib.search.data.repo.MagnetSearchRepo
import de.marxhendrik.skullandbones.rib.search.domain.SearchMagnetLinkUsecase
import de.marxhendrik.skullandbones.ributils.NoopNode

@Module
abstract class SearchModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        internal fun magnetSearchUseCase(repo: MagnetSearchRepo) = SearchMagnetLinkUsecase(repo)

        @Provides
        @JvmStatic
        internal fun searchApi(jsoup: JsoupApiBridge): MagnetSearchApi = BayMagnetSearchApi(jsoup)

        @Provides
        @JvmStatic
        internal fun node(): Node<Nothing> = NoopNode.create()

    }

}
