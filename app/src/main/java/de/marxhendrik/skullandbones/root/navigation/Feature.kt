package de.marxhendrik.skullandbones.root.navigation

import de.marxhendrik.skullandbones.core.navigation.addFragment
import de.marxhendrik.skullandbones.ui.MainActivity

private const val MAGNET_SEARCH_FRAGMENT =
    "de.marxhendrik.skullandbones.magnetsearch.ui.view.MagnetSearchFragment"

sealed class Feature {
    object MagnetSearch : Feature()
}


fun MainActivity.addFeature(feature: Feature) {
    when (feature) {
        is Feature.MagnetSearch -> addFragment(MAGNET_SEARCH_FRAGMENT)
    }
}
