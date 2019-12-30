package de.marxhendrik.skullandbones.rib.search

import android.os.Bundle
import com.badoo.ribs.core.Interactor
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView
import io.reactivex.disposables.CompositeDisposable

class SearchInteractor(
    savedInstanceState: Bundle?,
    private val disposable: CompositeDisposable = CompositeDisposable()
) : Interactor<SearchInputView>(savedInstanceState, disposable)
