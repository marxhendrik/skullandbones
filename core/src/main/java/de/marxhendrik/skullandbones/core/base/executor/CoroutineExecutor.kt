package de.marxhendrik.skullandbones.core.base.executor

import de.marxhendrik.skullandbones.core.base.Either
import de.marxhendrik.skullandbones.core.base.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class CoroutineExecutor : Executor {

    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val uiScope = CoroutineScope(Dispatchers.Main + job)


    override fun <T, R> execute(
        useCase: UseCase<T, R>,
        param: T,
        callback: (Either<Throwable, R>) -> Unit
    ) {
        ioScope.launch {
            try {
                val result = useCase(param)
                uiScope.launch { callback(Either.Right(result)) }
            } catch (t: Throwable) {
                callback(Either.Left(t))
            }
        }
    }


    override fun onCleared() {
        Timber.i("onCleared")
        job.cancel()
    }
}
