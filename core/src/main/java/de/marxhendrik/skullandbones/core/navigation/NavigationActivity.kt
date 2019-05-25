package de.marxhendrik.skullandbones.core.navigation

import androidx.fragment.app.FragmentManager

interface NavigationActivity {
    fun getSupportFragmentManager(): FragmentManager
    fun getClassLoader(): ClassLoader
}
