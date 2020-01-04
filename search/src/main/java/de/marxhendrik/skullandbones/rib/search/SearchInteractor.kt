package de.marxhendrik.skullandbones.rib.search

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import de.marxhendrik.skullandbones.core.base.Either
import de.marxhendrik.skullandbones.rib.search.builder.Search
import de.marxhendrik.skullandbones.rib.search.domain.RxSearchUsecase
import de.marxhendrik.skullandbones.rib.search.mapper.SearchResultToViewModel
import de.marxhendrik.skullandbones.rib.search.mapper.ViewEventToSearchQuery
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView
import io.reactivex.disposables.CompositeDisposable

class SearchInteractor(
    savedInstanceState: Bundle?,
    config: Search.Config,
    private val search: RxSearchUsecase,
    disposable: CompositeDisposable = CompositeDisposable()
) : Interactor<SearchInputView>(savedInstanceState, disposable) {

    private val searchResultToViewModel = SearchResultToViewModel(config)
    private val initialState = searchResultToViewModel(Either.Right(emptyList()))

    override fun onViewCreated(view: SearchInputView, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)

        view.accept(initialState)
        viewLifecycle.startStop {
            bind(search to view using searchResultToViewModel)
            bind(view to search using ViewEventToSearchQuery)
        }
    }

}
