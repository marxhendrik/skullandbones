package de.marxhendrik.skullandbones.magnetsearch.data


object Urls {
    private const val placeholderQuery = "{query}"
    private const val baysearch = ""
    fun baySearch(query: String) = baysearch.replace(placeholderQuery, query)
}
