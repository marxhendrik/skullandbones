package de.marxhendrik.skullandbones.core.base.livedata

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

fun <T> LiveData<T>.observe(fragment: Fragment, func: (T) -> Unit) = observe(fragment, Observer<T> { t -> func(t) })

fun <T, X> LiveData<T>.map(mapFunction: (T) -> X): LiveData<X> = Transformations.map(this, mapFunction)

fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> = Transformations.distinctUntilChanged(this)
