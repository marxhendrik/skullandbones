package de.marxhendrik.skullandbones

import android.app.Application
import de.marxhendrik.skullandbones.di.DaggerApplicationComponent

class SkullAndBonesApp : Application() {

    override fun onCreate() {
        DaggerApplicationComponent.builder()
            .bind(this)
            .build()
            .inject(this)

        super.onCreate()
    }
}
