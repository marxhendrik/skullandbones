package de.marxhendrik.skullandbones.core.base.usecase

interface UseCase<P, R> {
    suspend operator fun invoke(param: P): R
}
