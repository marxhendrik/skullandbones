package de.marxhendrik.skullandbones.rib.search.builder

import android.os.Bundle
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.view.RibView
import dagger.BindsInstance
import dagger.Component
import de.marxhendrik.skullandbones.core.di.scope.FeatureScope

@FeatureScope
@Component(modules = [SearchModule::class], dependencies = [Search.Dependency::class])
interface SearchComponent {

    @dagger.Component.Factory
    interface Factory {
        fun create(
            dependency: Search.Dependency,
            @BindsInstance savedInstanceState: Bundle?
        ): SearchComponent
    }

    fun node(): Node<Nothing>
}

interface MagnetSearchView : RibView
