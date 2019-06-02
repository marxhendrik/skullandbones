package de.marxhendrik.skullandbones.magnetsearch.ui.view.resultlist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import de.marxhendrik.skullandbones.magnetsearch.ui.model.UiMagnetSearchResult

@BindingAdapter("adapter")
fun RecyclerView.bindData(adapter: ResultListAdapter) {
    setAdapter(adapter)
}


@BindingAdapter("data")
fun RecyclerView.bindData(data: List<UiMagnetSearchResult>?) {
    data ?: return
    (adapter as ResultListAdapter).setData(data)
}
