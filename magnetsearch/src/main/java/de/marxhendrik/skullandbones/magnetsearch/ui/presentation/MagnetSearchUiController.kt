package de.marxhendrik.skullandbones.magnetsearch.ui.presentation

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import de.marxhendrik.skullandbones.core.base.livedata.map
import de.marxhendrik.skullandbones.magnetsearch.domain.SearchMagnetLinkUsecase
import de.marxhendrik.skullandbones.magnetsearch.ui.model.MagnetSearchUiModel
import de.marxhendrik.skullandbones.magnetsearch.ui.model.MagnetSearchViewModel
import de.marxhendrik.skullandbones.magnetsearch.ui.view.TextListener
import timber.log.Timber
import javax.inject.Inject

//TODO create in module
class MagnetSearchUiController @Inject constructor(
    private val viewModel: MagnetSearchViewModel,
    private var searchUsecase: SearchMagnetLinkUsecase
) : BaseObservable() {

    val title: LiveData<String>
        get() = viewModel.uiModel.map { it.title }

    var hint: String = "Search links"

    val textListener: TextListener = object : TextListener {
        override fun invoke(text: String) {
            Timber.i("query is:%s ", text)
            request(text)
        }
    }

    private fun request(query: String) {
        viewModel.execute(searchUsecase, query, { result ->
            result.on(
                failure = { Timber.e(it, "error") },
                success = {
                    viewModel.uiModel.value =
                        MagnetSearchUiModel(
                            it.getOrNull(0)?.title ?: ""
                        )
                }
            )
        })
    }
}

