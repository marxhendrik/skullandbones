package de.marxhendrik.skullandbones.magnetsearch.data


object Urls {
    private const val placeholderQuery = "{query}"
    private const val baysearch = "https://nothing/search/$placeholderQuery/0/99/0"
    fun baySearch(query: String) = baysearch.replace(placeholderQuery, query)
}
