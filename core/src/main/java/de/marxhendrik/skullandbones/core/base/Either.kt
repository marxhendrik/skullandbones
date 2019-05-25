package de.marxhendrik.skullandbones.core.base

sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    fun on(failure: (L) -> Any, success: (R) -> Any): Any =
        when (this) {
            is Left -> failure(a)
            is Right -> success(b)
        }
}

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> fn(b)
    }
