package de.marxhendrik.skullandbones.rib.search.ui

import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.LayoutRes
import com.badoo.ribs.android.Text
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.jakewharton.rxrelay2.PublishRelay
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView.Event.Search
import de.marxhendrik.skullandbones.search.R
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
            SearchInputViewImpl(
                inflate(it, layoutRes)
            )
        }
    }

    private val searchBox: SearchView = androidView.findViewById(R.id.searchView)

    init {
        setupSearchBox()
        setQueryListener()
    }

    private fun setQueryListener() {
        searchBox.setOnQueryTextListener(textListener {
            events.accept(Search(it))
        })

    }

    private fun setupSearchBox() {
        searchBox.setIconifiedByDefault(false)
        searchBox.isSubmitButtonEnabled = false
    }

    override fun accept(vm: SearchInputView.ViewModel?) {
        searchBox.queryHint = vm?.hintText?.resolve()
    }

    private fun Text?.resolve(): String? = this?.resolve(androidView.context)

}


fun textListener(callback: (String) -> Unit) =
    object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(q: String) = true
        override fun onQueryTextChange(newText: String): Boolean {
            callback(newText)
            return true
        }
    }
