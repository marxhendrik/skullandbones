package de.marxhendrik.skullandbones.magnetsearch.ui

import android.widget.SearchView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.core.base.livedata.map
import de.marxhendrik.skullandbones.core.base.viewmodel.BaseViewModel
import de.marxhendrik.skullandbones.magnetsearch.domain.MagnetSearchUseCase
import de.marxhendrik.skullandbones.magnetsearch.ui.uimodel.MagnetSearchUiModel
import timber.log.Timber
import javax.inject.Inject


class MagnetSearchViewModel(executor: Executor) : BaseViewModel(executor) {

    val uiModel = MutableLiveData<MagnetSearchUiModel>()

    init {
        Timber.i("new view model")
    }


}

//FIXME can we create this without injecting it into viewmodel? it would make sense to inject viewmodel in view for livedata
class MagnetSearchUiController @Inject constructor(
    private val viewModel: MagnetSearchViewModel, //<-- FIXME executor enough?
    private var searchUseCase: MagnetSearchUseCase
) : BaseObservable() {

    init {
        Timber.i("new ui controller")
    }

    val title: LiveData<String>
        get() = viewModel.uiModel.map { it.title }

    var text: String = "example"

    fun request(query: String) {
        viewModel.execute(searchUseCase, query, { result ->
            result.on(
                failure = { Timber.e(it, "error") },
                success = { viewModel.uiModel.value = MagnetSearchUiModel(it.getOrNull(0)?.title ?: "") }
            )
        })
    }

    /////////////// FIXME no likey

    private var query: String = ""

    @Bindable
    fun getQuery(): String {
        return query
    }


    fun getOnQueryTextListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String): Boolean = true

            override fun onQueryTextChange(newText: String): Boolean {
                Timber.i("query is:%s ", newText)
                query = newText
                request(query)
                return true
            }
        }
    }
}

@BindingAdapter("app:queryTextListener")
fun SearchView.bindQueryTextListener(listener: SearchView.OnQueryTextListener) {
    setOnQueryTextListener(listener)
}

@BindingAdapter("app:query")
fun SearchView.bindQuery(queryText: String) {
    setQuery(queryText, false)
}
