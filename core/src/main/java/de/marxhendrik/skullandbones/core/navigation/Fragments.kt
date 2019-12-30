package de.marxhendrik.skullandbones.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun NavigationActivity.addFragment(fragmentName: String) {
    val fragment =
        with(getSupportFragmentManager()) {
            findFragmentByTag(fragmentName)
                ?: fragmentFactory.instantiate(getClassLoader(), fragmentName)
        }

    if (!fragment.isAdded) addFragment(rootViewGroup.id, fragment, fragmentName)
}

private fun NavigationActivity.addFragment(rootId: Int, fragment: Fragment, tag: String) =
    getSupportFragmentManager().commit {
        add(rootId, fragment, tag)
    }
