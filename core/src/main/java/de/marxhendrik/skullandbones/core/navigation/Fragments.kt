package de.marxhendrik.skullandbones.core.navigation

import androidx.fragment.app.Fragment

private const val magnetSearchFragmentName = "de.marxhendrik.skullandbones.magnetsearch.ui.MagnetSearchFragment"

fun NavigationActivity.magnetSearchFragment(): Fragment =
    getSupportFragmentManager().fragmentFactory.instantiate(getClassLoader(), magnetSearchFragmentName)
