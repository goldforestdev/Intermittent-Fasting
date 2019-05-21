package com.goldforest.data.di

import androidx.room.Room
import com.goldforest.data.repository.DayResultRepositoryImpl
import com.goldforest.data.repository.PlanRepositoryImpl
import com.goldforest.data.source.DayResultDataSource
import com.goldforest.data.source.PlanDataSource
import com.goldforest.data.source.local.AppDatabase
import com.goldforest.data.source.remote.DayResultRemoteDataSource
import com.goldforest.data.source.remote.PlanRemoteDataSource
import com.goldforest.domain.repository.DayResultRepository
import com.goldforest.domain.repository.PlanRepository
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private const val DATABASE_NAME = "gfdiet.db"

private const val LOCAL_PLAN_DAO = "localPlanDao"
private const val REMOTE_PLAN_DAO = "remotePlanDao"

private const val LOCAL_DAY_RESULT_DAO = "localDayResultDao"
private const val REMOTE_DAY_RESULT_DAO = "reoteDayResultDao"

var dataModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME).build() }

    single<PlanDataSource>(StringQualifier(LOCAL_PLAN_DAO)) { get<AppDatabase>().planDao() }
    single<PlanDataSource>(StringQualifier(REMOTE_PLAN_DAO)) { PlanRemoteDataSource() }

    single<DayResultDataSource>(StringQualifier(LOCAL_DAY_RESULT_DAO)) { get<AppDatabase>().dayResultDao() }
    single<DayResultDataSource>(StringQualifier(REMOTE_DAY_RESULT_DAO)) { DayResultRemoteDataSource() }

    single<PlanRepository> {
        PlanRepositoryImpl(
            get(StringQualifier(LOCAL_PLAN_DAO)),
            get(StringQualifier(REMOTE_PLAN_DAO))
        )
    }
    single<DayResultRepository> {
        DayResultRepositoryImpl(
            get(StringQualifier(LOCAL_DAY_RESULT_DAO)),
            get(StringQualifier(REMOTE_DAY_RESULT_DAO))
        )
    }
}