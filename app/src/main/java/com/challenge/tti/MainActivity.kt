package com.challenge.tti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.challenge.tti.databinding.ActivityMainBinding
import com.challenge.tti.ui.main.listings.ListingsFragment
import dagger.hilt.android.AndroidEntryPoint

//Required annotation so that fragments living in this activity can also be used as EntryPoints
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)

    }
}