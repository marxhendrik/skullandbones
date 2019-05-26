package de.marxhendrik.skullandbones.magnetsearch.ui

import de.marxhendrik.skullandbones.core.base.fragment.BaseDataBindingFragment
import de.marxhendrik.skullandbones.magnetsearch.R
import de.marxhendrik.skullandbones.magnetsearch.databinding.FragmentMagnetSearchBinding
import de.marxhendrik.skullandbones.magnetsearch.di.buildAndInject
import javax.inject.Inject

class MagnetSearchFragment(
    override val layoutId: Int = R.layout.fragment_magnet_search
) : BaseDataBindingFragment<FragmentMagnetSearchBinding>() {

    @Inject
    lateinit var uiController: MagnetSearchUiController

    override fun onViewCreated() {
        Timber.i("onViewCreated")
    }
}
