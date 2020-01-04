package de.marxhendrik.skullandbones.ui

import android.os.Bundle
import android.view.ViewGroup
import com.badoo.ribs.android.ActivityStarter
import com.badoo.ribs.android.ActivityStarterImpl
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.android.Text
import com.badoo.ribs.android.requestcode.RequestCodeRegistry
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

    override fun createRib(savedInstanceState: Bundle?): Node<*> = SearchBuilder(
        // TODO provide dependencies from a Module
        object : Search.Dependency {
            override fun config() = Search.Config(Text.Resource(R.string.searchbox_hint))
            override fun activityStarter(): ActivityStarter =
                ActivityStarterImpl(this@RootActivity, RequestCodeRegistry(null))
        }

    ).build(savedInstanceState)
}
