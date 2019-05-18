package com.goldforest.capdiet.di

import com.goldforest.capdiet.viewmodel.MainViewModel
import com.goldforest.capdiet.viewmodel.PlanViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var viewModelModule = module {

    viewModel {
        MainViewModel()
        PlanViewModel()
    }
}

var DiModule = listOf(viewModelModule)

