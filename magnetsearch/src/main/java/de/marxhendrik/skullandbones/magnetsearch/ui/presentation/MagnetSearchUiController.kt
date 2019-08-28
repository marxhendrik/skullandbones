package de.marxhendrik.skullandbones.magnetsearch.ui.presentation

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.core.base.livedata.map
import de.marxhendrik.skullandbones.magnetsearch.data.model.SearchResult
import de.marxhendrik.skullandbones.magnetsearch.domain.SearchMagnetLinkUsecase
import de.marxhendrik.skullandbones.magnetsearch.ui.model.UiMagnetSearchModel
import de.marxhendrik.skullandbones.magnetsearch.ui.model.UiMagnetSearchResult
import de.marxhendrik.skullandbones.magnetsearch.ui.view.TextListener
import de.marxhendrik.skullandbones.magnetsearch.ui.view.resultlist.ResultListAdapter
import timber.log.Timber

class MagnetSearchUiController(
    private val uiModel: MutableLiveData<UiMagnetSearchModel>,
    executor: Executor,
    private var searchUsecase: SearchMagnetLinkUsecase
) : BaseObservable(), Executor by executor {

    //FIXME strings
    var hint: String = "Search links"

    val textListener: TextListener = object : TextListener {
        override fun invoke(text: String) {
            Timber.i("query is:%s ", text)
            request(text)
        }
    }

    val adapter = ResultListAdapter {
        Timber.i("onItemClicked: %s", it)
        //FIXME just fire intent for now
        //FIXME check for leaks, not sure this is cleaned up correctly
    }

    val adapterData: LiveData<List<UiMagnetSearchResult>>
        get() = uiModel.map { it.results }

    private fun request(query: String) {
        execute(searchUsecase, query, { result ->
            result.on(
                failure = { Timber.e(it, "error") },
                success = { updateData(it) }
            )
        })
    }

    private fun updateData(results: List<SearchResult?>) {
        uiModel.value = results.mapToUiModel()
    }

    private fun List<SearchResult?>.mapToUiModel(): UiMagnetSearchModel =
        UiMagnetSearchModel(mapNotNull { UiMagnetSearchResult(it!!.title, it.magnetLink) })
}


