package de.marxhendrik.skullandbones

import de.marxhendrik.skullandbones.magnetsearch.data.Urls
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.junit.Ignore
import org.junit.Test


/**
 * Do actual requests against api, so not meant as a real test, just for exploring the APIs
 */
@Ignore("should not run except when explicitly executed")
class ApiIntegrationTest {


    data class SearchResult(var title: String? = "", var magnetLink: String? = "", var fileSize: String? = "")


    @Test
    fun `play around with jsoup`() {
        val query = "game of thrones"

        //TODO find out how to get the size later

        val doc = Jsoup.connect(Urls.baySearch(query)).get()
        println(doc.title())

        val tableRow = doc
            .select("tr")

        val tableDeee = tableRow
            .select("td")

        println(tableDeee)
    }

    //FIXME clean up literals
    @Test
    fun `extract title and magnet link`() {
        val query = "game of thrones"

        Jsoup.connect(Urls.baySearch(query)).get()
            .select("tr")
            .select("td")
            .asSequence()
            .flatMap { getDivHref(it).zip(getLinks(it)).map { pair -> SearchResult(pair.first, pair.second) } }
            .forEach { println("# $it") }
    }

    private fun getLinks(it: Element) = it.select("a[href*=magnet:]").map { it.attr("href") }.asSequence()

    private fun getDivHref(it: Element) =
        it.select("div").select("a[href]").map { ref -> ref.getElementsByTag("a").text() }.asSequence()
}
