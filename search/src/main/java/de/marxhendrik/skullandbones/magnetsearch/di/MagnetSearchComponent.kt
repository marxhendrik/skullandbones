package de.marxhendrik.skullandbones.magnetsearch.di

import dagger.BindsInstance
import dagger.Component
import de.marxhendrik.skullandbones.core.di.CoreComponent
import de.marxhendrik.skullandbones.core.di.scope.FeatureScope
import de.marxhendrik.skullandbones.magnetsearch.ui.view.MagnetSearchFragment

@FeatureScope
@Component(modules = [MagnetSearchModule::class], dependencies = [CoreComponent::class])
interface MagnetSearchComponent {

    fun inject(fragment: MagnetSearchFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bind(fragment: MagnetSearchFragment): Builder

        fun core(coreComponent: CoreComponent): Builder

        fun build(): MagnetSearchComponent
    }
}
