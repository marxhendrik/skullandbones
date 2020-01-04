package de.marxhendrik.skullandbones.rib.search.mapper

import de.marxhendrik.skullandbones.core.base.Either
import de.marxhendrik.skullandbones.rib.search.builder.Search
import de.marxhendrik.skullandbones.rib.search.data.model.SearchResult
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView.ViewModel

class SearchResultToViewModel(
    private val config: Search.Config
) : (Either<Throwable, List<SearchResult>>) -> ViewModel {

    override fun invoke(response: Either<Throwable, List<SearchResult>>): ViewModel =
        when (response) {
            is Either.Right -> ViewModel(
                config.hintText,
                response.value.map { SearchInputView.SearchResult(it.title, it.magnetLink) }
            )
            is Either.Left -> ViewModel(
                config.hintText,
                emptyList(),
                response.value
            )
        }
}
