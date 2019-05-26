package de.marxhendrik.skullandbones.magnetsearch.ui

import de.marxhendrik.skullandbones.core.base.fragment.BaseFragment
import de.marxhendrik.skullandbones.magnetsearch.R
import de.marxhendrik.skullandbones.magnetsearch.di.buildAndInject
import kotlinx.android.synthetic.main.fragment_magnet_search.*
import timber.log.Timber
import javax.inject.Inject

class MagnetSearchFragment(override val layoutId: Int = R.layout.fragment_magnet_search) : BaseFragment() {

    @Inject
    lateinit var uiController: MagnetSearchUiController

    override fun onViewCreated() {
        Timber.i("onViewCreated")
        uiController.title.observe { tv.text = it }

        uiController.request("John Wick")
    }
}
