package de.marxhendrik.skullandbones.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import de.marxhendrik.skullandbones.R
import de.marxhendrik.skullandbones.core.navigation.NavigationActivity
import de.marxhendrik.skullandbones.core.navigation.magnetSearchFragment

class MainActivity : AppCompatActivity(), NavigationActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(magnetSearchFragment())
    }

    private fun addFragment(fragment: Fragment) =
        supportFragmentManager.commit {
            add(R.id.root, fragment)
        }
}
