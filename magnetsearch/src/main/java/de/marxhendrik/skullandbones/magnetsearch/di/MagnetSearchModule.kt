package de.marxhendrik.skullandbones.magnetsearch.di

import dagger.BindsInstance
import dagger.Component
import dagger.Module
import de.marxhendrik.skullandbones.core.di.CoreComponent
import de.marxhendrik.skullandbones.coreComponent
import de.marxhendrik.skullandbones.magnetsearch.ui.MagnetSearchFragment

@Module
object MagnetSearchModule

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

fun MagnetSearchFragment.inject() {
    DaggerMagnetSearchComponent.builder()
        .bind(this)
        .core(coreComponent())
        .build()
        .inject(this)
}
