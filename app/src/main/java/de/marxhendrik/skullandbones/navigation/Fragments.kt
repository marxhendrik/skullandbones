package de.marxhendrik.skullandbones.navigation

import androidx.fragment.app.Fragment
import de.marxhendrik.skullandbones.ui.MainActivity

private const val magnetSearchFragmentName = "de.marxhendrik.skullandbones.magnetsearch.ui.MagnetSearchFragment"

fun MainActivity.magnetSearchFragment(): Fragment =
    supportFragmentManager.fragmentFactory.instantiate(classLoader, magnetSearchFragmentName)
