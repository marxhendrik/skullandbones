package de.marxhendrik.skullandbones.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import de.marxhendrik.skullandbones.R
import de.marxhendrik.skullandbones.core.navigation.NavigationActivity
import de.marxhendrik.skullandbones.root.navigation.Feature
import de.marxhendrik.skullandbones.root.navigation.addFeature
import kotlinx.android.synthetic.main.activity_root.*

class RootActivity : AppCompatActivity(), NavigationActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        addFeature(Feature.MagnetSearch)
    }

    override val rootViewGroup: ViewGroup
        get() = root
}
