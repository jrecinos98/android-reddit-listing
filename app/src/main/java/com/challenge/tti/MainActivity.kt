package com.challenge.tti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.challenge.tti.ui.main.listings.ListingsFragment
import dagger.hilt.android.AndroidEntryPoint

//Required annotation so that fragments living in this activity can also be used as EntryPoints
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListingsFragment.newInstance())
                .commitNow()
        }
    }
}