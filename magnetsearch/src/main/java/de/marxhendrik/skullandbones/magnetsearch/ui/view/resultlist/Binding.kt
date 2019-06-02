package de.marxhendrik.skullandbones.magnetsearch.ui.view.resultlist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import de.marxhendrik.skullandbones.magnetsearch.ui.model.UiMagnetSearchResult

@BindingAdapter("adapter")
fun RecyclerView.bindData(adapter: ResultListAdapter) {
    setAdapter(adapter)
}


@BindingAdapter(value = ["data"])
fun RecyclerView.bindData(data: List<UiMagnetSearchResult>?) {
    (adapter as? ResultListAdapter)?.run { data?.let { setData(data) } }
}
