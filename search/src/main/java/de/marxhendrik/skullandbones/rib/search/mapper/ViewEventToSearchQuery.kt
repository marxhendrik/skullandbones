package de.marxhendrik.skullandbones.rib.search.mapper

import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView

object ViewEventToSearchQuery : (SearchInputView.Event) -> String {
    override fun invoke(event: SearchInputView.Event): String = when (event) {
        is SearchInputView.Event.Search -> event.query
    }
}
