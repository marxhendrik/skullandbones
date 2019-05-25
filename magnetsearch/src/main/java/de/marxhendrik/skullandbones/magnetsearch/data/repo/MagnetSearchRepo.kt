package de.marxhendrik.skullandbones.magnetsearch.data.repo

import de.marxhendrik.skullandbones.magnetsearch.data.api.MagnetSearchApi
import de.marxhendrik.skullandbones.magnetsearch.data.model.SearchResult
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class MagnetSearchRepo @Inject constructor(private val searchApi: MagnetSearchApi) {

    suspend fun search(query: String): List<SearchResult> {
        return suspendCoroutine { continuation ->
            continuation.resumeWith(Result.success(searchApi.search(query)))
        }
    }
}
