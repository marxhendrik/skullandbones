package de.marxhendrik.skullandbones.magnetsearch.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import de.marxhendrik.skullandbones.core.base.BaseFragment
import de.marxhendrik.skullandbones.magnetsearch.R
import de.marxhendrik.skullandbones.magnetsearch.data.Urls
import kotlinx.android.synthetic.main.fragment_magnet_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import kotlin.coroutines.suspendCoroutine

class MagnetSearchFragment(override val layoutId: Int = R.layout.fragment_magnet_search) : BaseFragment() {

    val job = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + job)
    val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query = "game of thrones"


        ioScope.launch {
            val result = request(query)
            Log.i("bla", result.toString()) //Timber FIXME
            uiScope.launch {
                tv.text = result[0].title
            }
        }


    }

    override fun onPause() {
        job.cancel()
        super.onPause()
    }

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
