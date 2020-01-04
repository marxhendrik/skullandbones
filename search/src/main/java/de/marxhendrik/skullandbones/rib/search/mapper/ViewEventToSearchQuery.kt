package de.marxhendrik.skullandbones.rib.search.mapper

import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView

object ViewEventToSearchQuery : (SearchInputView.Event.Search) -> String {
    override fun invoke(event: SearchInputView.Event.Search): String = event.query
}
