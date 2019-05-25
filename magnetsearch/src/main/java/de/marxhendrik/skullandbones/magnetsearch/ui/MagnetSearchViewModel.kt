package de.marxhendrik.skullandbones.magnetsearch.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import de.marxhendrik.skullandbones.magnetsearch.domain.MagnetSearchUseCase
import kotlinx.coroutines.Job
import javax.inject.Inject

class MagnetSearchViewModel @Inject constructor(var searchUseCase: MagnetSearchUseCase) : ViewModel() {

    // FIXME move execution part to executor
    private val job = Job()

    fun requestResult(callback: (List<MagnetSearchUseCase.SearchResult>) -> Unit) {
        searchUseCase.requestResult(job, callback)
    }

    override fun onCleared() {
        Log.i("ViewModel", "onCleared")//FIXME timber
        job.cancel()
    }

}
