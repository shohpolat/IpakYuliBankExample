package com.shoh.ipakyolibankexample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.shoh.ipakyolibankexample.R
import com.shoh.ipakyolibankexample.databinding.ActivityMainBinding
import com.shoh.ipakyolibankexample.ui.firstFragment.FirstFragment

class Main : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        title = ""

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        when (navHostFragment.childFragmentManager.fragments[0]) {
            is FirstFragment -> {
                if ((navHostFragment.childFragmentManager.fragments[0] as FirstFragment).isBottomSheetOpen) {
                    (navHostFragment.childFragmentManager.fragments[0] as FirstFragment).hideBottomSheet()
                }else {
                    super.onBackPressed()
                }
            }
            else -> {
                super.onBackPressed()
            }
        }

    }

}