package de.marxhendrik.skullandbones.magnetsearch.ui

import androidx.lifecycle.MutableLiveData
import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.core.base.viewmodel.BaseViewModel
import de.marxhendrik.skullandbones.magnetsearch.ui.uimodel.MagnetSearchUiModel


class MagnetSearchViewModel(executor: Executor) : BaseViewModel(executor) {
    val uiModel = MutableLiveData<MagnetSearchUiModel>()
}
