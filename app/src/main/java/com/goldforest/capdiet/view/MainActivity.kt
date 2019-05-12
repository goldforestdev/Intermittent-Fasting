package com.goldforest.capdiet.view

import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseActivity
import com.goldforest.capdiet.databinding.ActivityMainBinding
import com.goldforest.capdiet.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {
    }
}
