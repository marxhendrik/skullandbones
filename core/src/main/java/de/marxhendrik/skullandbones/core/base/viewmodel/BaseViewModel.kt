package de.marxhendrik.skullandbones.core.base.viewmodel

import androidx.lifecycle.ViewModel
import de.marxhendrik.skullandbones.core.base.executor.Executor

abstract class BaseViewModel(private val executor: Executor) : ViewModel(), Executor by executor {

    override fun onCleared() {
        executor.onCleared()
    }
}

