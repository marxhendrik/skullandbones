package de.marxhendrik.skullandbones.rib.search.util

import com.jakewharton.rxrelay2.PublishRelay
import de.marxhendrik.skullandbones.core.base.Either
import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.core.base.usecase.UseCase
import io.reactivex.Observer

// This is currently my compromise to wrap the usecase into an rx-interface while not using the MVICore Reducer.
// I dont like about this (and about the MVICore reducers) that they are themselves responsible for the handling
// of subscriptions (by being disposable) and that they are themselves responsible for "executing" which in my
// previous architecture is done by the Executor. This makes the Reducer/Usecase do unnecessary stuff which needs
// to be repeated over and over in every UseCase... I might still look at it again at a later time to see if I have
// more insights. Also I dislike using RxJava on this layer which is why I chose to inject the other usecase and the
// executor here and continue using Coroutines in the background
abstract class RxUsecaseAdapter<P, R, UC : UseCase<P, R>>(
    private val usecase: UC,
    private val executor: Executor
) : RxUsecase<P, Either<Throwable, R>> {

    private val resultRelay =
        PublishRelay.create<Either<Throwable, R>>()

    override fun accept(query: P) {
        executor.execute(usecase, query) {
            resultRelay.accept(this)
        }
    }

    override fun subscribe(observer: Observer<in Either<Throwable, R>>) =
        resultRelay.subscribe(observer)

    override fun isDisposed(): Boolean = executor.isCleared()

    override fun dispose() = executor.onCleared()
}
