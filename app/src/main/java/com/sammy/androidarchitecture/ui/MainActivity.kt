package com.sammy.androidarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sammy.androidarchitecture.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}