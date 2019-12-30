package de.marxhendrik.skullandbones.search.di

import dagger.BindsInstance
import dagger.Component
import de.marxhendrik.skullandbones.core.di.CoreComponent
import de.marxhendrik.skullandbones.core.di.scope.FeatureScope

@FeatureScope
@Component(modules = [MagnetSearchModule::class], dependencies = [CoreComponent::class])
interface MagnetSearchComponent {

    fun inject(fragment: MagnetSearchView)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bind(fragment: MagnetSearchView): Builder

        fun core(coreComponent: CoreComponent): Builder

        fun build(): MagnetSearchComponent
    }
}

interface MagnetSearchView
