package de.marxhendrik.skullandbones.navigation

import android.content.Context
import androidx.fragment.app.Fragment

enum class Fragments {
    INSTANCE;

    private val magnetSearchFragmentName = "de.marxhendrik.skullandbones.magnetsearch.ui.MagnetSearchFragment"

    fun magnetSearchFragment(context: Context): Fragment = Fragment.instantiate(context, magnetSearchFragmentName)

}
