package de.marxhendrik.skullandbones.magnetsearch.ui.presentation

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import de.marxhendrik.skullandbones.core.base.livedata.map
import de.marxhendrik.skullandbones.magnetsearch.data.model.SearchResult
import de.marxhendrik.skullandbones.magnetsearch.domain.SearchMagnetLinkUsecase
import de.marxhendrik.skullandbones.magnetsearch.ui.model.MagnetSearchViewModel
import de.marxhendrik.skullandbones.magnetsearch.ui.model.UiMagnetSearchModel
import de.marxhendrik.skullandbones.magnetsearch.ui.model.UiMagnetSearchResult
import de.marxhendrik.skullandbones.magnetsearch.ui.view.TextListener
import de.marxhendrik.skullandbones.magnetsearch.ui.view.resultlist.ResultListAdapter
import timber.log.Timber
import javax.inject.Inject

//TODO create in module
class MagnetSearchUiController @Inject constructor(
    private val viewModel: MagnetSearchViewModel,
    private var searchUsecase: SearchMagnetLinkUsecase
) : BaseObservable() {

    var hint: String = "Search links" //FIXME

    val textListener: TextListener = object : TextListener {
        override fun invoke(text: String) {
            Timber.i("query is:%s ", text)
            request(text)
        }
    }

    val adapter = ResultListAdapter {
        Timber.i("onItemClicked: %s", it)
    }

    val adapterData: LiveData<List<UiMagnetSearchResult>>
        get() = viewModel.uiModel.map { it.results }

    private fun request(query: String) {
        viewModel.execute(searchUsecase, query, { result ->
            result.on(
                failure = { Timber.e(it, "error") },
                success = { updateData(it) }
            )
        })
    }

    private fun updateData(results: List<SearchResult?>) {
        viewModel.uiModel.value = results.mapToUiModel()
    }

    private fun List<SearchResult?>.mapToUiModel(): UiMagnetSearchModel =
        UiMagnetSearchModel(mapNotNull { UiMagnetSearchResult(it!!.title, it.magnetLink) })
}


