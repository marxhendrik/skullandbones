package de.marxhendrik.skullandbones.magnetsearch.ui

import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.core.base.viewmodel.BaseViewModel
import de.marxhendrik.skullandbones.magnetsearch.data.model.SearchResult
import de.marxhendrik.skullandbones.magnetsearch.domain.MagnetSearchUseCase
import timber.log.Timber

class MagnetSearchViewModel(
    executor: Executor,
    private var searchUseCase: MagnetSearchUseCase
) : BaseViewModel(executor) {

    // FIXME something something UiController?!
    fun requestResult(callback: (List<SearchResult>) -> Unit) {
        execute(searchUseCase, "John Wick", { result ->
            result.on(
                failure = { Timber.e(it, "error") },
                success = callback
            )
        })
    }
}
