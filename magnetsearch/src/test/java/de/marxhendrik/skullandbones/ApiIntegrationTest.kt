package de.marxhendrik.skullandbones

import org.jsoup.Jsoup
import org.junit.Ignore
import org.junit.Test


/**
 * Do actual requests against api, so not meant as a real test, just for exploring the APIs
 */
@Ignore("should not run except when explicitly executed")
class ApiIntegrationTest {


    data class SearchResult(var title: String? = "", var magnetLink: String? = "", var fileSize: String? = "")


    @Test
    fun `test the soup`() {
        val query = "game of thrones"


        val doc = Jsoup.connect("https://no-website-i-ever-heard-of.org/search/$query/0/99/0").get()
        println(doc.title())

        val tableRow = doc
            .select("tr")

        println(tableRow)

        val tableDeee = tableRow
            .select("td")

        println(tableDeee)

        println("################")
//        val names = tableDeee.select("div").select("a[href]")
//
//        for (name in names) {
//            println(name.getElementsByTag("a").text())
//        }

        //  FIXME: so how do I make it in one go now?

        //TITLES here
        tableDeee
            .flatMap { it.select("div").select("a[href]") }
            .map { it.getElementsByTag("a").text() }
            .forEach { println("# $it") }


        //LINKS here
        println("####### parsed links #########")
        val magnetlinks = tableDeee
            .select("a[href*=magnet:]")
        for (magnet in magnetlinks) {
            val result = SearchResult(
                magnet.attr("href")
            )
            println(result)
        }

//        println(magnetlinks)

//        for (headline in newsHeadlines) {
//            println(
//                "%s\n\t%s".format(
//                    headline.attr("title"), headline.absUrl("href")
//                )
//            )
//        }

        println()
        println()
        println()
        println()
        println()
        println()
        println(magnetlinks)

    }
}
