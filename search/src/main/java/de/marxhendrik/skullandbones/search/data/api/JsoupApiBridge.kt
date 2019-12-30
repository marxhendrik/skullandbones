package de.marxhendrik.skullandbones.search.data.api

import org.jsoup.Jsoup
import javax.inject.Inject

class JsoupApiBridge @Inject constructor() {

    fun connect(url: String) = Jsoup.connect(url)
}
