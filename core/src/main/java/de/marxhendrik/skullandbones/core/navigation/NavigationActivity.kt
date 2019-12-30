package de.marxhendrik.skullandbones.core.navigation

import android.view.ViewGroup
import androidx.fragment.app.FragmentManager

interface NavigationActivity {
    val rootViewGroup: ViewGroup
    fun getSupportFragmentManager(): FragmentManager
    fun getClassLoader(): ClassLoader
}
