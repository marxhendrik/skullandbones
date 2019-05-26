package de.marxhendrik.skullandbones.magnetsearch.ui

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import de.marxhendrik.skullandbones.core.base.livedata.map
import de.marxhendrik.skullandbones.magnetsearch.domain.MagnetSearchUseCase
import de.marxhendrik.skullandbones.magnetsearch.ui.uimodel.MagnetSearchUiModel
import de.marxhendrik.skullandbones.magnetsearch.ui.view.TextListener
import timber.log.Timber
import javax.inject.Inject

class MagnetSearchUiController @Inject constructor(
    private val viewModel: MagnetSearchViewModel,
    private var searchUseCase: MagnetSearchUseCase
) : BaseObservable() {

    val title: LiveData<String>
        get() = viewModel.uiModel.map { it.title }

    var hint: String = "Search links"

    private fun request(query: String) {
        viewModel.execute(searchUseCase, query, { result ->
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

    val textListener: TextListener = object : TextListener {
        override fun invoke(text: String) {
            Timber.i("query is:%s ", text)
            request(text)
        }
    }
}

