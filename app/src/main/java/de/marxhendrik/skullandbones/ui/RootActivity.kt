package de.marxhendrik.skullandbones.ui

import android.os.Bundle
import android.view.ViewGroup
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.core.Node
import de.marxhendrik.skullandbones.R
import de.marxhendrik.skullandbones.core.navigation.NavigationActivity
import de.marxhendrik.skullandbones.rib.search.builder.Search
import de.marxhendrik.skullandbones.rib.search.builder.SearchBuilder
import de.marxhendrik.skullandbones.root.navigation.Feature
import de.marxhendrik.skullandbones.root.navigation.addFeature
import kotlinx.android.synthetic.main.activity_root.*

class RootActivity : RibActivity(), NavigationActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_root)
        super.onCreate(savedInstanceState)
        addFeature(Feature.MagnetSearch)
    }

    override val rootViewGroup: ViewGroup
        get() = rootId

    override fun createRib(savedInstanceState: Bundle?): Node<*> {
        return SearchBuilder(
            object : Search.Dependency {}

        ).build(savedInstanceState)
    }
}
