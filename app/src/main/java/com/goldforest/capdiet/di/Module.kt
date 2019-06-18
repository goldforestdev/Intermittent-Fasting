package com.goldforest.capdiet.di

import com.goldforest.capdiet.view.calendar.CalendarContract
import com.goldforest.capdiet.view.calendar.CalendarPresenter
import com.goldforest.capdiet.view.dayResult.DayResultContract
import com.goldforest.capdiet.view.dayResult.DayResultPresenter
import com.goldforest.capdiet.viewmodel.MainViewModel
import com.goldforest.capdiet.viewmodel.PlanViewModel
import com.goldforest.data.di.dataModule
import com.goldforest.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var viewModelModule = module {

    viewModel {
        MainViewModel()
        PlanViewModel(androidContext())
    }

    factory<CalendarContract.Presenter> { CalendarPresenter(get(), get()) }
    factory<DayResultContract.Presenter> { DayResultPresenter(get()) }
}

var DiModule = listOf(viewModelModule, dataModule, domainModule)

