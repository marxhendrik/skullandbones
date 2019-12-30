package de.marxhendrik.skullandbones.search.di


fun MagnetSearchView.buildAndInject() {
    DaggerMagnetSearchComponent.builder()
        .bind(this)
//        .core(coreComponent())<--- Dependency inteface
        .build()
        .inject(this)
}
