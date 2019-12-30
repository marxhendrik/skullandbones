package de.marxhendrik.skullandbones.rib.search.data.api

import de.marxhendrik.skullandbones.rib.search.data.model.SearchResult

interface MagnetSearchApi {
    fun search(query: String): List<SearchResult>
}
