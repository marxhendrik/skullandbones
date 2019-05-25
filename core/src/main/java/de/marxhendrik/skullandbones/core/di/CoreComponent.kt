package de.marxhendrik.skullandbones.core.di

import dagger.Component

@Component(modules = [CoreModule::class])
interface CoreComponent {

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
    }

}
