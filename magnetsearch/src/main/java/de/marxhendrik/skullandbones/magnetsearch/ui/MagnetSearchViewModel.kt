package de.marxhendrik.skullandbones.magnetsearch.ui

import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.core.base.viewmodel.BaseViewModel
import de.marxhendrik.skullandbones.magnetsearch.data.model.SearchResult
import de.marxhendrik.skullandbones.magnetsearch.domain.MagnetSearchUseCase
import timber.log.Timber
import javax.inject.Inject

class MagnetSearchViewModel(executor: Executor) : BaseViewModel(executor)

//FIXME can we create this without injecting it into viewmodel? it would make sense to inject viewmodel in view for livedata
class MagnetSearchUiController @Inject constructor(
    private val viewModel: MagnetSearchViewModel,
    private var searchUseCase: MagnetSearchUseCase
) {

    fun requestResult(callback: (List<SearchResult>) -> Unit) {
        viewModel.execute(searchUseCase, "John Wick", { result ->
            result.on(
                failure = { Timber.e(it, "error") },
                success = callback
            )
        })
    }
}
