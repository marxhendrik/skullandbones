package de.marxhendrik.skullandbones.magnetsearch.domain

import de.marxhendrik.skullandbones.core.base.usecase.UseCase
import de.marxhendrik.skullandbones.magnetsearch.data.Urls
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import timber.log.Timber
import kotlin.coroutines.suspendCoroutine

class MagnetSearchUseCase : UseCase<String, List<MagnetSearchUseCase.SearchResult>> {

    override suspend operator fun invoke(param: String) = request(param)

    private suspend fun request(query: String): List<SearchResult> {
        return suspendCoroutine { continuation ->
            Timber.i("try call jsoup")
            val result = Jsoup.connect(Urls.baySearch(query)).get()
                .select("tr")
                .select("td")
                .asSequence()
                .flatMap { getDivHref(it).zip(getLinks(it)).map { pair -> SearchResult(pair.first, pair.second) } }
                .toList()

            continuation.resumeWith(Result.success(result))
        }
    }

    private fun getLinks(it: Element) = it.select("a[href*=magnet:]").map { it.attr("href") }.asSequence()

    private fun getDivHref(it: Element) =
        it.select("div").select("a[href]").map { ref -> ref.getElementsByTag("a").text() }.asSequence()

    data class SearchResult(var title: String? = "", var magnetLink: String? = "", var fileSize: String? = "")
}
