package de.marxhendrik.skullandbones.ributils

import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.Rib

/**
 * Noop Classes for Ribs which can be used to test stuff during development
 */

object NoopNode : Rib {
    fun create(): Node<Nothing> =
        Node(
            savedInstanceState = null,
            identifier = object : NoopRib {},
            viewFactory = null,
            router = null,
            interactor = object : Interactor<Nothing>(
                savedInstanceState = null,
                disposables = null
            ) {}
        )
}

interface NoopRib : Rib
