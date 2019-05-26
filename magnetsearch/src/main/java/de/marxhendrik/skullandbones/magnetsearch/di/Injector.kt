package de.marxhendrik.skullandbones.magnetsearch.di

import de.marxhendrik.skullandbones.coreComponent
import de.marxhendrik.skullandbones.magnetsearch.ui.MagnetSearchFragment

fun MagnetSearchFragment.buildAndInject() {
    DaggerMagnetSearchComponent.builder()
        .bind(this)
        .core(coreComponent())
        .build()
        .inject(this)
}
