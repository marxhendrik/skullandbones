package de.marxhendrik.skullandbones.magnetsearch.ui.view

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import de.marxhendrik.skullandbones.core.base.fragment.DataBindingFragment
import de.marxhendrik.skullandbones.magnetsearch.BR
import de.marxhendrik.skullandbones.magnetsearch.R
import de.marxhendrik.skullandbones.magnetsearch.di.buildAndInject
import de.marxhendrik.skullandbones.magnetsearch.ui.presentation.MagnetSearchUiController
import kotlinx.android.synthetic.main.magnet_search.*
import javax.inject.Inject

class MagnetSearchFragment(
    override val layoutId: Int = R.layout.magnet_search
) : DataBindingFragment<MagnetSearchUiController>() {

    @Inject
    lateinit var uiController: MagnetSearchUiController

    override fun getBindingData(): BindingData<MagnetSearchUiController> = BindingData(BR.data, uiController)

    override fun inject() = buildAndInject()

    override fun onViewCreated() {
        configureSearchView()
        configureRecyclerView()
    }

    private fun configureSearchView() {
        searchView.setIconifiedByDefault(false)
        searchView.isSubmitButtonEnabled = false
    }

    private fun configureRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        list.layoutManager = layoutManager
        list.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
    }
}
