package de.marxhendrik.skullandbones.rib.search.ui

import com.badoo.ribs.android.Text
import com.badoo.ribs.core.view.RibView
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface SearchInputView :
    RibView,
    ObservableSource<SearchInputView.Event>,
    Consumer<SearchInputView.ViewModel> {

    data class ViewModel(
        val hintText: Text
    )

    sealed class Event {
        data class Search(val query: String) : Event()
    }

}
