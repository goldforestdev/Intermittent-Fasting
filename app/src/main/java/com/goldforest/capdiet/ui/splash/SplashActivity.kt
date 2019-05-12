package com.goldforest.capdiet.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.goldforest.capdiet.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)
    }
}
