package com.sammy.androidarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sammy.androidarchitecture.R
import com.sammy.androidarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //initialize navhost fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //init nav controller
        val navController:NavController = navHostFragment.navController
        //init appbar configuration
        val appBarConfiguration:AppBarConfiguration = AppBarConfiguration(navController.graph)
        //set up toolbar using nav controller
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
    }
}