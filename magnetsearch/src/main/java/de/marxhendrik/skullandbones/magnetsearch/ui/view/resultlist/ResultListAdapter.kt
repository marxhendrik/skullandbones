package de.marxhendrik.skullandbones.magnetsearch.ui.view.resultlist

import de.marxhendrik.skullandbones.core.base.adapter.DataBindingAdapter
import de.marxhendrik.skullandbones.magnetsearch.BR
import de.marxhendrik.skullandbones.magnetsearch.R
import de.marxhendrik.skullandbones.magnetsearch.ui.model.UiMagnetSearchResult


class ResultListAdapter : DataBindingAdapter<UiMagnetSearchResult>(BR.item) {
    override fun getItemViewType(position: Int): Int = R.layout.list_item
}
