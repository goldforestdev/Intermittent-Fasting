package com.goldforest.domain.di

import com.goldforest.domain.usercase.*
import com.goldforest.domain.usercase.impl.*
import org.koin.dsl.module

var domainModule = module {
    single<CreatePlan> { CreatePlanUsecase(get()) }
    single<CreateDayResult> { CreateDayResultUsecase(get()) }
    single<GetPlans> { GetPlansUsecase(get()) }
    single<GetAllDayResultsByMonth> { GetAllDayResultsByMonthUsecase(get()) }
    single<GetDayResult> { GetDayResultUsecase(get()) }
    single<HasActivePlan> { HasActivePlanUsecase(get()) }
}