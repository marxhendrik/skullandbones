package de.marxhendrik.skullandbones.rib.search.ui

import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.badoo.ribs.android.Text
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.jakewharton.rxrelay2.PublishRelay
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView.Event.Search
import de.marxhendrik.skullandbones.rib.search.util.textListener
import de.marxhendrik.skullandbones.search.R
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer
import timber.log.Timber

interface SearchInputView :
    RibView,
    ObservableSource<SearchInputView.Event>,
    Consumer<SearchInputView.ViewModel> {

    data class ViewModel(
        val hintText: Text,
        val resultItems: List<SearchResult>,
        val error: Throwable? = null
    )

    data class SearchResult(val itemTitle: String)

    sealed class Event {
        data class Search(val query: String) : Event()
    }

    interface Factory : ViewFactory<Nothing?, SearchInputView>
}

class SearchInputViewImpl(
    override val androidView: ViewGroup,
    private val events: PublishRelay<SearchInputView.Event> = PublishRelay.create()
) : SearchInputView,
    ObservableSource<SearchInputView.Event> by events {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.search_input
    ) : SearchInputView.Factory {
        override fun invoke(deps: Nothing?): (ViewGroup) -> SearchInputViewImpl = {
            SearchInputViewImpl(inflate(it, layoutRes))
        }
    }

    private val searchBox: SearchView = androidView.findViewById(R.id.searchView)

    init {
        searchBox.setIconifiedByDefault(false)
        searchBox.isSubmitButtonEnabled = false
        searchBox.setOnQueryTextListener(textListener {
            events.accept(Search(it))
        })
    }

    override fun accept(vm: SearchInputView.ViewModel?) {
        searchBox.queryHint = vm?.hintText?.resolve()
        vm?.error?.let { Toast.makeText(androidView.context, it.message, Toast.LENGTH_LONG) }

        Timber.i("results: ${vm?.resultItems}")
    }

    private fun Text?.resolve(): String? = this?.resolve(androidView.context)

}
