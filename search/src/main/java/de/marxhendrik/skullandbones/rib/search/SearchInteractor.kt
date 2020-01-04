package de.marxhendrik.skullandbones.rib.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.android.ActivityStarter
import com.badoo.ribs.core.Interactor
import de.marxhendrik.skullandbones.core.base.Either
import de.marxhendrik.skullandbones.rib.search.builder.Search
import de.marxhendrik.skullandbones.rib.search.domain.RxSearchUsecase
import de.marxhendrik.skullandbones.rib.search.mapper.SearchResultToViewModel
import de.marxhendrik.skullandbones.rib.search.mapper.ViewEventToSearchQuery
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class SearchInteractor(
    savedInstanceState: Bundle?,
    config: Search.Config,
    private val search: RxSearchUsecase,
    private val activityStarter: ActivityStarter,
    disposable: CompositeDisposable = CompositeDisposable()
) : Interactor<SearchInputView>(savedInstanceState, disposable) {

    private val searchResultToViewModel = SearchResultToViewModel(config)
    private val initialState = searchResultToViewModel(Either.Right(emptyList()))
    private val send = Consumer<String> {
        activityStarter.startActivity {
            Intent(Intent.ACTION_VIEW, Uri.parse(it))
        }
    }

    override fun onViewCreated(view: SearchInputView, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)

        view.accept(initialState)
        viewLifecycle.startStop {
            bind(search to view using searchResultToViewModel)
            bind(
                //FIXME I am sure there is a better way to do this
                Observable.wrap(view).filter { it is SearchInputView.Event.Search }
                    .cast(SearchInputView.Event.Search::class.java) to search using ViewEventToSearchQuery
            )
            bind(
                //FIXME I am sure there is a better way to do this
                Observable.wrap(view).filter { it is SearchInputView.Event.Selected }
                    .cast(SearchInputView.Event.Selected::class.java) to send using { it.link }
            )
        }
    }

}
