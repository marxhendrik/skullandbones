package de.marxhendrik.skullandbones.magnetsearch.domain

import de.marxhendrik.skullandbones.core.base.usecase.UseCase
import de.marxhendrik.skullandbones.magnetsearch.data.model.SearchResult
import de.marxhendrik.skullandbones.magnetsearch.data.repo.MagnetSearchRepo
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class SearchMagnetLinkUsecase(private val repo: MagnetSearchRepo) : UseCase<String, List<SearchResult?>> {

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
