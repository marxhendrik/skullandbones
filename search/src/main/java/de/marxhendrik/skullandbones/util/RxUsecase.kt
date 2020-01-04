package de.marxhendrik.skullandbones.util

import io.reactivex.ObservableSource
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

interface RxUsecase<P, R> : Consumer<P>,
    ObservableSource<R>,
    Disposable
