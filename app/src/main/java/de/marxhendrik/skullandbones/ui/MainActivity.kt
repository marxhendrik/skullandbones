package de.marxhendrik.skullandbones.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.marxhendrik.skullandbones.R
import de.marxhendrik.skullandbones.core.navigation.NavigationActivity
import de.marxhendrik.skullandbones.core.navigation.addMagnetSearchFragment

class MainActivity : AppCompatActivity(), NavigationActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addMagnetSearchFragment(R.id.root)
    }
}
