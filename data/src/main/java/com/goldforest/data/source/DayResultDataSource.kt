package com.goldforest.data.source

import com.goldforest.data.model.DayResultEntity

interface DayResultDataSource {
    suspend fun save(vararg dayResult: DayResultEntity)

    suspend fun getAllByPlanId(planId: Long): Array<DayResultEntity>

    suspend fun getAllByMonth(startTime: Long, endTime: Long): Array<DayResultEntity>

    suspend fun get(id: Long): DayResultEntity

    suspend fun delete(vararg dayResult: DayResultEntity)
}