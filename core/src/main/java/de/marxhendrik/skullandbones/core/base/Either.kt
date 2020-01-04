package de.marxhendrik.skullandbones.core.base

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()

    data class Right<out R>(val value: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun on(failure: (L) -> Unit, success: (R) -> Unit) {
        when (this) {
            is Left -> failure(value)
            is Right -> success(value)
        }
    }
}

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(value)
        is Either.Right -> fn(value)
    }
