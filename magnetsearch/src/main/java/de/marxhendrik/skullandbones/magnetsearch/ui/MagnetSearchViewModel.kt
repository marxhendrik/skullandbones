package de.marxhendrik.skullandbones.magnetsearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.core.base.viewmodel.BaseViewModel
import de.marxhendrik.skullandbones.magnetsearch.domain.MagnetSearchUseCase
import de.marxhendrik.skullandbones.magnetsearch.ui.uimodel.MagneSearchUiModel
import timber.log.Timber
import javax.inject.Inject

class MagnetSearchViewModel(executor: Executor) : BaseViewModel(executor) {

    val searchResult = MutableLiveData<MagneSearchUiModel>()

}

//FIXME can we create this without injecting it into viewmodel? it would make sense to inject viewmodel in view for livedata
class MagnetSearchUiController @Inject constructor(
    private val viewModel: MagnetSearchViewModel, //<-- FIXME executor enough?
    private var searchUseCase: MagnetSearchUseCase
) {

    val searchResult: LiveData<MagneSearchUiModel>
        get() = viewModel.searchResult

    fun request(query: String) {
        viewModel.execute(searchUseCase, query, { result ->
            result.on(
                failure = { Timber.e(it, "error") },
                success = { viewModel.searchResult.value = MagneSearchUiModel(it[0].title ?: "") }
            )
        })
    }
}
