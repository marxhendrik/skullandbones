package de.marxhendrik.skullandbones.magnetsearch.data.api

import org.jsoup.Jsoup
import javax.inject.Inject

class JsoupApiBridge @Inject constructor() {

    fun connect(url: String) = Jsoup.connect(url)
}
