package de.marxhendrik.skullandbones.rib.search

import android.os.Bundle
import com.badoo.ribs.core.Builder
import de.marxhendrik.skullandbones.rib.DaggerSearchComponent

class SearchBuilder(override val dependency: Search.Dependency) : Builder<Search.Dependency>() {
    fun build(savedInstanceState: Bundle?) =
        DaggerSearchComponent
            .factory()
            .create(
                dependency,
                savedInstanceState
            )
            .node()
}
