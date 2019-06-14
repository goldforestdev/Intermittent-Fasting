package com.goldforest.domain.di

import com.goldforest.domain.usercase.CreatePlan
import com.goldforest.domain.usercase.GetAllDayResultsByMonth
import com.goldforest.domain.usercase.GetDayResult
import com.goldforest.domain.usercase.HasActivePlan
import com.goldforest.domain.usercase.impl.CreatePlanUsecase
import com.goldforest.domain.usercase.impl.GetAllDayResultsByMonthUsecase
import com.goldforest.domain.usercase.impl.GetDayResultUsecase
import com.goldforest.domain.usercase.impl.HasActivePlanUsecase
import org.koin.dsl.module

var domainModule = module {
    single<CreatePlan> { CreatePlanUsecase(get()) }
    single<GetAllDayResultsByMonth> { GetAllDayResultsByMonthUsecase(get()) }
    single<GetDayResult> { GetDayResultUsecase(get()) }
    single<HasActivePlan> { HasActivePlanUsecase(get()) }
}