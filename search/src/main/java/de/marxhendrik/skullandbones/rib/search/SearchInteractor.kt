package de.marxhendrik.skullandbones.rib.search

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import com.badoo.ribs.core.Interactor
import de.marxhendrik.skullandbones.rib.search.builder.Search
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView
import io.reactivex.disposables.CompositeDisposable

class SearchInteractor(
    savedInstanceState: Bundle?,
    config: Search.Config,
    disposable: CompositeDisposable = CompositeDisposable()
) : Interactor<SearchInputView>(savedInstanceState, disposable) {

    private val initialState = SearchInputView.ViewModel(config.hintText)

    override fun onViewCreated(view: SearchInputView, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)

        view.accept(initialState)
    }
}
