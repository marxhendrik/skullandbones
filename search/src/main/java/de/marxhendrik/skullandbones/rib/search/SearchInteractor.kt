package de.marxhendrik.skullandbones.rib.search

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.jakewharton.rxrelay2.PublishRelay
import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.rib.search.builder.Search
import de.marxhendrik.skullandbones.rib.search.domain.SearchUsecase
import de.marxhendrik.skullandbones.rib.search.mapper.ViewEventToSearchQuery
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class SearchInteractor(
    savedInstanceState: Bundle?,
    config: Search.Config,
    private val searchUseCase: SearchUsecase,
    private val executor: Executor,
    private val disposable: CompositeDisposable = CompositeDisposable()
) : Interactor<SearchInputView>(savedInstanceState, disposable) {

    private val initialState = SearchInputView.ViewModel(config.hintText)
    private val searchRequestRelay = PublishRelay.create<String>()

    override fun onViewCreated(view: SearchInputView, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)

        view.accept(initialState)

        viewLifecycle.startStop {
            bind(view to searchRequestRelay using ViewEventToSearchQuery)
        }

        subscribeToSearch()


    }

    private fun subscribeToSearch() {
        disposable.add(searchRequestRelay.subscribe { request ->
            executor.execute(searchUseCase, request) {
                on(
                    failure = { Timber.e(it, "error") },
                    success = {
                        //should attach result rib and provide result as Observable Source from here
                        Timber.i("reults: $it")
                    }
                )
            }
        })
    }
}
