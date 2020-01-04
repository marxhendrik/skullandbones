package de.marxhendrik.skullandbones.rib.search.util

import io.reactivex.ObservableSource
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

interface RxUsecase<P, R> : Consumer<P>,
    ObservableSource<R>,
    Disposable
