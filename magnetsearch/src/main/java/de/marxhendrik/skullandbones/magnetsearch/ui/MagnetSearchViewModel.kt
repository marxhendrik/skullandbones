package de.marxhendrik.skullandbones.magnetsearch.ui

import androidx.lifecycle.ViewModel
import de.marxhendrik.skullandbones.magnetsearch.domain.MagnetSearchUseCase
import kotlinx.coroutines.Job
import timber.log.Timber
import javax.inject.Inject

class MagnetSearchViewModel @Inject constructor(var searchUseCase: MagnetSearchUseCase) : ViewModel() {

    // FIXME move execution part to executor
    private val job = Job()

    fun requestResult(callback: (List<MagnetSearchUseCase.SearchResult>) -> Unit) {
        searchUseCase.requestResult(job) { either ->
            either.on(
                failure = {
                    Timber.e(it, "error") //FIXME
                },
                success = callback
            )
        }
    }

    override fun onCleared() {
        job.cancel()
    }

}
