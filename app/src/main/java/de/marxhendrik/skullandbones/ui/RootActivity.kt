package de.marxhendrik.skullandbones.ui

import android.os.Bundle
import android.view.ViewGroup
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.android.Text
import com.badoo.ribs.core.Node
import de.marxhendrik.skullandbones.R
import de.marxhendrik.skullandbones.core.navigation.NavigationActivity
import de.marxhendrik.skullandbones.rib.search.builder.Search
import de.marxhendrik.skullandbones.rib.search.builder.SearchBuilder
import kotlinx.android.synthetic.main.activity_root.*

class RootActivity : RibActivity(), NavigationActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_root)
        super.onCreate(savedInstanceState)
    }

    override val rootViewGroup: ViewGroup
        get() = rootId

    override fun createRib(savedInstanceState: Bundle?): Node<*> {

        //FIXME REMOVE WHEN 'SEARCH' IS FINISHED
//        addFeature(Feature.MagnetSearch)

        return SearchBuilder(
            // TODO provide dependencies from a Module
            object : Search.Dependency {
                override fun config() = Search.Config(Text.Resource(R.string.searchbox_hint))
            }

        ).build(savedInstanceState)
    }
}
