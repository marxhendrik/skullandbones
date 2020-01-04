package de.marxhendrik.skullandbones.rib.search.util

import android.widget.SearchView

fun textListener(callback: (String) -> Unit) =
    object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(q: String) = true
        override fun onQueryTextChange(newText: String): Boolean {
            callback(newText)
            return true
        }
    }
