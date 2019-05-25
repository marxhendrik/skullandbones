package de.marxhendrik.skullandbones.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import de.marxhendrik.skullandbones.R
import de.marxhendrik.skullandbones.navigation.Fragments

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            add(R.id.root, Fragments.INSTANCE.magnetSearchFragment(this@MainActivity))
        }
    }
}
