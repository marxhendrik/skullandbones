package de.marxhendrik.skullandbones.magnetsearch.ui.model

import androidx.lifecycle.MutableLiveData
import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.core.base.viewmodel.BaseViewModel

class MagnetSearchViewModel(executor: Executor) : BaseViewModel(executor) {
    val uiModel = MutableLiveData<MagnetSearchUiModel>()
}
