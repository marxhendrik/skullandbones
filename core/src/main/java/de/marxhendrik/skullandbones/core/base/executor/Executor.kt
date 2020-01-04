package de.marxhendrik.skullandbones.core.base.executor

import de.marxhendrik.skullandbones.core.base.Either
import de.marxhendrik.skullandbones.core.base.usecase.UseCase

interface Executor {
    fun onCleared()
    fun <T, R> execute(
        useCase: UseCase<T, R>,
        param: T,
        callback: (Either<Throwable, R>).() -> Unit
    )

    fun isCleared(): Boolean
}
