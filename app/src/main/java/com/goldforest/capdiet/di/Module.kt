package com.goldforest.capdiet.di

import com.goldforest.capdiet.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var viewModelModule = module {

    viewModel {
        MainViewModel()
    }
}

var DiModule = listOf(viewModelModule)

