package de.marxhendrik.skullandbones.rib.search.builder

import android.os.Bundle
import com.badoo.ribs.core.Interactor
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
        internal fun magnetSearchUseCase(repo: MagnetSearchRepo) = SearchMagnetLinkUsecase(repo)

        @Provides
        @JvmStatic
        internal fun searchApi(jsoup: JsoupApiBridge): MagnetSearchApi = BayMagnetSearchApi(jsoup)

        @Provides
        @JvmStatic
        internal fun node(
            savedInstanceState: Bundle?,
            interactor: Interactor<Nothing>
        ): Node<Nothing> = Node(
            savedInstanceState = savedInstanceState,
            identifier = object : Search {},
            viewFactory = null,
            router = null,
            interactor = interactor
        )

        @Provides
        @JvmStatic
        internal fun interactor(savedInstanceState: Bundle?) =
            object : Interactor<Nothing>(
                savedInstanceState = savedInstanceState,
                disposables = null
            ) {}

    }

}
