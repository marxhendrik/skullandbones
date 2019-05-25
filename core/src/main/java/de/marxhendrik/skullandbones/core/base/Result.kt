package de.marxhendrik.skullandbones.core.base

sealed class Result<out S, out F> {
    data class Success<out L>(val a: L) : Result<L, Nothing>()
    data class Failure<out R>(val b: R) : Result<Nothing, R>()

    val isSucess get() = this is Failure<F>
    val isFailure get() = this is Success<S>

    fun <L> success(a: L) = Success(a)
    fun <R> failure(b: R) = Failure(b)

    fun on(success: (S) -> Any, failure: (F) -> Any): Any =
        when (this) {
            is Success -> success(a)
            is Failure -> failure(b)
        }
}

fun <T, L, R> Result<L, R>.flatMap(fn: (R) -> Result<L, T>): Result<L, T> =
    when (this) {
        is Result.Success -> Result.Success(a)
        is Result.Failure -> fn(b)
    }
