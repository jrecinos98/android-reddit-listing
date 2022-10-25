package com.challenge.tti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.challenge.tti.ui.main.MainFragment
import com.challenge.tti.ui.main.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}