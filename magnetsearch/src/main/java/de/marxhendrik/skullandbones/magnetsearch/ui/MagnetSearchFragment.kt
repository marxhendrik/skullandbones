package de.marxhendrik.skullandbones.magnetsearch.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import de.marxhendrik.skullandbones.core.base.BaseFragment
import de.marxhendrik.skullandbones.magnetsearch.R
import de.marxhendrik.skullandbones.magnetsearch.data.Urls
import de.marxhendrik.skullandbones.magnetsearch.di.inject
import kotlinx.android.synthetic.main.fragment_magnet_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import kotlin.coroutines.suspendCoroutine

class MagnetSearchFragment(override val layoutId: Int = R.layout.fragment_magnet_search) : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inject()
        super.onViewCreated(view, savedInstanceState)

        requestResult {
            tv.text = it[0].title
        }

    }

    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private fun requestResult(callback: (List<SearchResult>) -> Unit) {
        ioScope.launch {
            val result = request("game of thrones")
            uiScope.launch {
                callback(result)
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
