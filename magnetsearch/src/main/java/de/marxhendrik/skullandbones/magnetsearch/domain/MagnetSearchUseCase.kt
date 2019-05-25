package de.marxhendrik.skullandbones.magnetsearch.domain

import android.util.Log
import de.marxhendrik.skullandbones.core.base.Either
import de.marxhendrik.skullandbones.magnetsearch.data.Urls
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class MagnetSearchUseCase @Inject constructor() {

    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    fun requestResult(parentJob: Job, callback: (Either<Exception, List<SearchResult>>) -> Unit): Job {
        ioScope.launch(parentJob) {
            val result = request("game of thrones")
            uiScope.launch {
                callback(Either.Right(result))
            }
        }

        return job
    }

    // FIXME figure out what happens with Exceptions
    private suspend fun request(query: String): List<SearchResult> {
        return suspendCoroutine { continuation ->
            Log.i("bla", "try") //Timber FIXME

            try {
                val result = Jsoup.connect(Urls.baySearch(query)).get()
                    .select("tr")
                    .select("td")
                    .asSequence()
                    .flatMap { getDivHref(it).zip(getLinks(it)).map { pair -> SearchResult(pair.first, pair.second) } }
                    .toList()

                Log.i("bla", "resume") //Timber FIXME
                continuation.resumeWith(Result.success(result))
            } catch (e: Exception) {
                Log.e("bla", "error", e) //Timber FIXME
                continuation.resumeWith(Result.failure(e))
            }
        }
    }

    private fun getLinks(it: Element) = it.select("a[href*=magnet:]").map { it.attr("href") }.asSequence()

    private fun getDivHref(it: Element) =
        it.select("div").select("a[href]").map { ref -> ref.getElementsByTag("a").text() }.asSequence()

    data class SearchResult(var title: String? = "", var magnetLink: String? = "", var fileSize: String? = "")
}
