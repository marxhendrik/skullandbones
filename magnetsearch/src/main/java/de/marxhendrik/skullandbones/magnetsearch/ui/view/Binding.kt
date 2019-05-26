package de.marxhendrik.skullandbones.magnetsearch.ui.view

import android.widget.SearchView
import androidx.databinding.BindingAdapter

@BindingAdapter("queryTextListener")
fun SearchView.bindQueryTextListener(callback: TextListener) {
    setOnQueryTextListener(makeSearchViewOnQueryListener(callback))
}


fun makeSearchViewOnQueryListener(callback: TextListener) = object : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(q: String) = true
    override fun onQueryTextChange(newText: String): Boolean {
        callback(newText)
        return true
    }
}

/**
 * Makes sure that UiController does not need to know view classes
 */
interface TextListener {
    operator fun invoke(text: String)
}
