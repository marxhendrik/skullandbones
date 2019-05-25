package de.marxhendrik.skullandbones.di

import dagger.BindsInstance
import dagger.Component
import de.marxhendrik.skullandbones.SkullAndBonesApp

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: SkullAndBonesApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bind(application: SkullAndBonesApp): Builder

        fun build(): ApplicationComponent
    }

}
