package de.marxhendrik.skullandbones.rib.search

import android.os.Bundle
import android.os.Parcelable
import com.badoo.ribs.core.Router
import com.badoo.ribs.core.routing.action.AttachRibRoutingAction.Companion.attach
import com.badoo.ribs.core.routing.action.RoutingAction
import de.marxhendrik.skullandbones.rib.search.SearchRouter.Configuration
import de.marxhendrik.skullandbones.rib.search.SearchRouter.Configuration.Empty
import de.marxhendrik.skullandbones.rib.search.ui.SearchInputView
import kotlinx.android.parcel.Parcelize

class SearchRouter(
    savedInstanceState: Bundle?
) : Router<Configuration, Nothing, Configuration, Nothing, SearchInputView>(
    savedInstanceState = savedInstanceState,
    initialConfiguration = Empty
) {

    sealed class Configuration : Parcelable {
        @Parcelize
        object SearchResultList : Configuration()

        @Parcelize
        object Empty : Configuration()
    }

    override fun resolveConfiguration(configuration: Configuration): RoutingAction<SearchInputView> =
        when (configuration) {
            is Configuration.SearchResultList -> attach {
                TODO("create resultlist rib")
            }
            Empty -> TODO("create empty Rib")
        }
}
