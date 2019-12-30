package de.marxhendrik.skullandbones.search.data.api

import de.marxhendrik.skullandbones.search.data.model.SearchResult

interface MagnetSearchApi {
    fun search(query: String): List<SearchResult>
}
