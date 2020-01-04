package de.marxhendrik.skullandbones.rib.search.builder

import com.badoo.ribs.android.ActivityStarter
import com.badoo.ribs.android.Text
import com.badoo.ribs.core.Rib
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputViewImpl

interface Search : Rib {
    interface Dependency {
        fun config(): Config
        fun activityStarter(): ActivityStarter
    }

    class Customisation(
        val viewFactory: SearchInputView.Factory = SearchInputViewImpl.Factory()
    )

    data class Config(
        val hintText: Text
    )
}
