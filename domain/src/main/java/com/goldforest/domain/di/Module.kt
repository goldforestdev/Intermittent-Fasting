package com.goldforest.domain.di

import com.goldforest.domain.usercase.CreatePlan
import com.goldforest.domain.usercase.GetAllDayResults
import com.goldforest.domain.usercase.HasActivePlan
import com.goldforest.domain.usercase.impl.CreatePlanUsecase
import com.goldforest.domain.usercase.impl.GetAllDayResultsUsecase
import com.goldforest.domain.usercase.impl.HasActivePlanUsecase
import org.koin.dsl.module

var domainModule = module {
    single<CreatePlan> { CreatePlanUsecase(get()) }
    single<GetAllDayResults> { GetAllDayResultsUsecase(get()) }
    single<HasActivePlan> { HasActivePlanUsecase(get()) }
}