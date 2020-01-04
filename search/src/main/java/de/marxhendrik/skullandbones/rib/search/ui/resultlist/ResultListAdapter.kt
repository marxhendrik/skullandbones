package de.marxhendrik.skullandbones.rib.search.ui.resultlist

import de.marxhendrik.skullandbones.core.base.adapter.DataBindingAdapter
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView
import de.marxhendrik.skullandbones.search.BR
import de.marxhendrik.skullandbones.search.R

class ResultListAdapter : DataBindingAdapter<SearchInputView.SearchResult>(BR.item) {

    private var onItemClicked: (SearchInputView.SearchResult) -> Unit = {}

    override fun getItemViewType(position: Int): Int = R.layout.list_item

    override fun onItemClick(item: SearchInputView.SearchResult) {
        onItemClicked(item)
    }

    fun setOnItemClickListener(listener: (SearchInputView.SearchResult) -> Unit) {
        onItemClicked = listener
    }
}
