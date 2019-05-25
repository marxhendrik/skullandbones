package de.marxhendrik.skullandbones.magnetsearch.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import de.marxhendrik.skullandbones.core.base.BaseFragment
import de.marxhendrik.skullandbones.magnetsearch.R
import de.marxhendrik.skullandbones.magnetsearch.di.inject
import kotlinx.android.synthetic.main.fragment_magnet_search.*
import javax.inject.Inject

class MagnetSearchFragment(override val layoutId: Int = R.layout.fragment_magnet_search) : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<MagnetSearchViewModel>(factoryProducer = { factory })

    override fun onViewCreated() {
        inject()

        viewModel.requestResult {
            tv.text = it[0].title
        }

    }
}
