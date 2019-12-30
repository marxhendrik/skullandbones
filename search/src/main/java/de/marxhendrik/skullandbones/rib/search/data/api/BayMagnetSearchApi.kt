package de.marxhendrik.skullandbones.rib.search.data.api

import de.marxhendrik.skullandbones.rib.search.data.Urls
import de.marxhendrik.skullandbones.rib.search.data.model.SearchResult
import org.jsoup.nodes.Element
import timber.log.Timber

//FIXME extract constants
class BayMagnetSearchApi(private val jsoup: JsoupApiBridge) :
    MagnetSearchApi {

    override fun search(query: String): List<SearchResult> {
        Timber.i("try call jsoup")
        return jsoup.connect(Urls.baySearch(query)).get()
            .select("tr")
            .select("td")
            .asSequence()
            .flatMap { extractResult(it) }
            .toList()
    }

    private fun extractResult(it: Element) =
        getDivHref(it).zip(getLinks(it)).map { pair ->
            SearchResult(
                pair.first,
                pair.second
            )
        }

    private fun getLinks(it: Element) = it.select("a[href*=magnet:]").map { it.attr("href") }.asSequence()

    private fun getDivHref(it: Element) =
        it.select("div").select("a[href]").map { ref -> ref.getElementsByTag("a").text() }.asSequence()
}
