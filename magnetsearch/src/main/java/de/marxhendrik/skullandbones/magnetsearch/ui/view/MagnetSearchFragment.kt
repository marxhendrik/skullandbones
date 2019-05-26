package de.marxhendrik.skullandbones.magnetsearch.ui.view

import de.marxhendrik.skullandbones.core.base.fragment.BaseDataBindingFragment
import de.marxhendrik.skullandbones.magnetsearch.R
import de.marxhendrik.skullandbones.magnetsearch.databinding.MagnetSearchBinding
import de.marxhendrik.skullandbones.magnetsearch.di.buildAndInject
import de.marxhendrik.skullandbones.magnetsearch.ui.presentation.MagnetSearchUiController
import kotlinx.android.synthetic.main.magnet_search.*
import javax.inject.Inject

class MagnetSearchFragment(
    override val layoutId: Int = R.layout.magnet_search
) : BaseDataBindingFragment<MagnetSearchBinding>() {

    @Inject
    lateinit var uiController: MagnetSearchUiController

    override fun bind(binding: MagnetSearchBinding) {
        binding.data = uiController
    }

    override fun inject() = buildAndInject()

    override fun onViewCreated() {
        searchView.setIconifiedByDefault(false)
        searchView.isSubmitButtonEnabled = false
    }
}
