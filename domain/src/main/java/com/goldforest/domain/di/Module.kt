package com.goldforest.domain.di

import com.goldforest.domain.usercase.*
import com.goldforest.domain.usercase.impl.*
import org.koin.dsl.module

var domainModule = module {
    single<CreatePlan> { CreatePlanUsecase(get()) }
    single<GetAllDayResultsByMonth> { GetAllDayResultsByMonthUsecase(get()) }
    single<GetDayResult> { GetDayResultUsecase(get()) }
    single<HasActivePlan> { HasActivePlanUsecase(get()) }
    single<GetPlanListResultPaging> { GetPlanListResultPagingUsecase(get()) }
}