package com.goldforest.capdiet.di

import com.goldforest.capdiet.viewmodel.CalendarViewModel
import com.goldforest.capdiet.viewmodel.MainViewModel
import com.goldforest.capdiet.viewmodel.PlanViewModel
import com.goldforest.data.di.dataModule
import com.goldforest.domain.di.domainModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var viewModelModule = module {

    viewModel {
        MainViewModel()
        PlanViewModel()
        CalendarViewModel(get())
    }
}

var DiModule = listOf(viewModelModule, dataModule, domainModule)

