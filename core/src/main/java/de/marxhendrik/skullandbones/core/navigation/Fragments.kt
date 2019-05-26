package de.marxhendrik.skullandbones.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

private const val magnetSearchFragmentName = "de.marxhendrik.skullandbones.magnetsearch.ui.view.MagnetSearchFragment"

fun NavigationActivity.addMagnetSearchFragment(rootId: Int) = addFragment(magnetSearchFragmentName, rootId)

private fun NavigationActivity.addFragment(fragmentName: String, rootId: Int) {
    val fragment =
        with(getSupportFragmentManager()) {
            findFragmentByTag(fragmentName)
                ?: fragmentFactory.instantiate(getClassLoader(), fragmentName)
        }

    if (!fragment.isAdded) addFragment(rootId, fragment, fragmentName)
}

private fun NavigationActivity.addFragment(rootId: Int, fragment: Fragment, tag: String) =
    getSupportFragmentManager().commit {
        add(rootId, fragment, tag)
    }
