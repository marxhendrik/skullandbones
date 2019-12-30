package de.marxhendrik.skullandbones.rib.search.builder

import android.os.Bundle
import com.badoo.ribs.core.Node
import dagger.Module
import dagger.Provides
import de.marxhendrik.skullandbones.rib.search.SearchInteractor
import de.marxhendrik.skullandbones.rib.search.SearchRouter
import de.marxhendrik.skullandbones.rib.search.data.api.BayMagnetSearchApi
import de.marxhendrik.skullandbones.rib.search.data.api.JsoupApiBridge
import de.marxhendrik.skullandbones.rib.search.data.api.MagnetSearchApi
import de.marxhendrik.skullandbones.rib.search.data.repo.MagnetSearchRepo
import de.marxhendrik.skullandbones.rib.search.domain.SearchMagnetLinkUsecase
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView

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
        internal fun node(
            savedInstanceState: Bundle?,
            customisation: Search.Customisation,
            interactor: SearchInteractor,
            router: SearchRouter
        ): Node<SearchInputView> = Node(
            savedInstanceState = savedInstanceState,
            identifier = object : Search {},
            viewFactory = customisation.viewFactory(null),
            interactor = interactor,
            router = router
        )

        @Provides
        @JvmStatic
        internal fun interactor(savedInstanceState: Bundle?) = SearchInteractor(savedInstanceState)

        @Provides
        @JvmStatic
        internal fun router(savedInstanceState: Bundle?) = SearchRouter(savedInstanceState)

    }

}
