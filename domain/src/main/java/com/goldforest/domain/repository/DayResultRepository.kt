package com.goldforest.domain.repository

import com.goldforest.domain.exceptions.NotExistDayResultException
import com.goldforest.domain.model.DayResult

interface DayResultRepository {
    suspend fun save(vararg dayResult: DayResult)

    @Throws(NotExistDayResultException::class)
    suspend fun getAllByPlanId(planId: Long): List<DayResult>

    @Throws(NotExistDayResultException::class)
    suspend fun getAllByMonth(startTime: Long, endTime: Long): List<DayResult>

    @Throws(NotExistDayResultException::class)
    suspend fun get(id: Long): DayResult

    suspend fun delete(vararg dayResult: DayResult)
}