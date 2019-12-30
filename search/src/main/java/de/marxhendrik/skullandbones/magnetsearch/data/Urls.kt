package de.marxhendrik.skullandbones.magnetsearch.data


object Urls {
    private const val host = ""
    private const val protocol = "https://"
    private const val prefix = "/search/"
    private const val postfix = "/0/99/0"
    fun baySearch(query: String) = "$protocol$host$prefix$query$postfix"
}
