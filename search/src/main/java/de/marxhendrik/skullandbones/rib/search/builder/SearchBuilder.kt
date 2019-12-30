package de.marxhendrik.skullandbones.rib.search.builder

import android.os.Bundle
import com.badoo.ribs.core.Builder

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
