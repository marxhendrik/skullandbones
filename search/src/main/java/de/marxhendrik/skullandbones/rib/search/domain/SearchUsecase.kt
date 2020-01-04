package de.marxhendrik.skullandbones.rib.search.domain

import de.marxhendrik.skullandbones.core.base.executor.Executor
import de.marxhendrik.skullandbones.core.base.usecase.UseCase
import de.marxhendrik.skullandbones.rib.search.data.model.SearchResult
import de.marxhendrik.skullandbones.rib.search.data.repo.MagnetSearchRepo
import de.marxhendrik.skullandbones.util.RxUsecaseAdapter
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class SearchUsecase(private val repo: MagnetSearchRepo) :
    UseCase<String, List<SearchResult>> {

    private val debounceRate: Long = 600
    private var query: String? = null

    override suspend operator fun invoke(param: String): List<SearchResult> {
        query = param
        delay(debounceRate)
        return suspendCoroutine {
            if (query != param) {
                it.context.cancel()
            } else {
                it.resume(repo.search(param))
            }
        }
    }
}


/**
 * Wraps coroutine based SearchUseCase into Rx based UseCase which is Consumer of the query String
 * and ObservableSource of Either<Throwable,SearchResult> which plays better with MVICore patterns
 */
class RxSearchUsecase(searchUsecase: SearchUsecase, executor: Executor) :
    RxUsecaseAdapter<String, List<SearchResult>, SearchUsecase>(searchUsecase, executor)
