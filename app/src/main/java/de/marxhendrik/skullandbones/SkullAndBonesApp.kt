package de.marxhendrik.skullandbones

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import de.marxhendrik.skullandbones.core.di.CoreComponent
import de.marxhendrik.skullandbones.core.di.DaggerCoreComponent

class SkullAndBonesApp : Application() {

    private val coreComponent: CoreComponent by lazy { DaggerCoreComponent.create() }

    companion object {
        fun coreComponent(context: Context) =
            (context.applicationContext as SkullAndBonesApp).coreComponent
    }
}

fun Activity.coreComponent() = SkullAndBonesApp.coreComponent(this)
fun Fragment.coreComponent() = SkullAndBonesApp.coreComponent(this.context!!)
