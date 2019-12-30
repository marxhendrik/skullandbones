package de.marxhendrik.skullandbones.search.data.repo

import de.marxhendrik.skullandbones.search.data.api.MagnetSearchApi
import de.marxhendrik.skullandbones.search.data.model.SearchResult
import javax.inject.Inject

class MagnetSearchRepo @Inject constructor(private val searchApi: MagnetSearchApi) {
    fun search(query: String): List<SearchResult> = searchApi.search(query)
}
