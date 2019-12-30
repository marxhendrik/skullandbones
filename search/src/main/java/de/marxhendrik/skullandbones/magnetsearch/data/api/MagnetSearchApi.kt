package de.marxhendrik.skullandbones.magnetsearch.data.api

import de.marxhendrik.skullandbones.magnetsearch.data.model.SearchResult

interface MagnetSearchApi {
    fun search(query: String): List<SearchResult>
}
