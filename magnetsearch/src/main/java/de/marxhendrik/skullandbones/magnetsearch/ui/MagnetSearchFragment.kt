package de.marxhendrik.skullandbones.magnetsearch.ui

import de.marxhendrik.skullandbones.core.base.fragment.BaseFragment
import de.marxhendrik.skullandbones.magnetsearch.R
import de.marxhendrik.skullandbones.magnetsearch.di.inject
import kotlinx.android.synthetic.main.fragment_magnet_search.*
import javax.inject.Inject

class MagnetSearchFragment(override val layoutId: Int = R.layout.fragment_magnet_search) : BaseFragment() {

    @Inject
    lateinit var viewModel: MagnetSearchUiController

    override fun onViewCreated() {
        inject()

        // FIXME databinding
        viewModel.request("John Wick")

        viewModel.searchResult.observe {
            tv.text = it.title
        }
    }
}
