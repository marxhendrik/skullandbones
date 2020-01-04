package de.marxhendrik.skullandbones.rib.search.builder

import android.os.Bundle
import com.badoo.ribs.android.ActivityStarter
import com.badoo.ribs.core.Node
import dagger.Module
import dagger.Provides
import de.marxhendrik.skullandbones.core.base.executor.CoroutineExecutor
import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.rib.search.SearchInteractor
import de.marxhendrik.skullandbones.rib.search.SearchRouter
import de.marxhendrik.skullandbones.rib.search.data.api.BayMagnetSearchApi
import de.marxhendrik.skullandbones.rib.search.data.api.JsoupApiBridge
import de.marxhendrik.skullandbones.rib.search.data.api.MagnetSearchApi
import de.marxhendrik.skullandbones.rib.search.data.repo.MagnetSearchRepo
import de.marxhendrik.skullandbones.rib.search.domain.RxSearchUsecase
import de.marxhendrik.skullandbones.rib.search.domain.SearchUsecase
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView

@Module
abstract class SearchModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        internal fun searchUseCase(repo: MagnetSearchRepo) = SearchUsecase(repo)

        @Provides
        @JvmStatic
        internal fun rxSearchUseCase(
            searchUsecase: SearchUsecase,
            executor: Executor
        ) = RxSearchUsecase(searchUsecase, executor)

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
        internal fun interactor(
            savedInstanceState: Bundle?,
            config: Search.Config,
            usecase: RxSearchUsecase,
            activityStarter: ActivityStarter
        ) = SearchInteractor(savedInstanceState, config, usecase, activityStarter)

        @Provides
        @JvmStatic
        internal fun router(savedInstanceState: Bundle?) = SearchRouter(savedInstanceState)

        @Provides
        @JvmStatic
        internal fun customization() = Search.Customisation()

        @Provides
        @JvmStatic
        fun executor(): Executor = CoroutineExecutor()

    }

}
