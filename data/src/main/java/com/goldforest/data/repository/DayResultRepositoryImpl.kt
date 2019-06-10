package com.goldforest.data.repository

import com.goldforest.data.model.toEntity
import com.goldforest.data.model.toModel
import com.goldforest.data.source.DayResultDataSource
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.repository.DayResultRepository

class DayResultRepositoryImpl(
    private val dayResultLocalDataSource: DayResultDataSource,
    private val dayResultRemoteDataSource: DayResultDataSource
): DayResultRepository {

    override suspend fun save(vararg dayResult: DayResult) {
        val dayResultEntityList = dayResult.map { it.toEntity() }.toTypedArray()
        dayResultLocalDataSource.save(*dayResultEntityList)
        dayResultRemoteDataSource.save(*dayResultEntityList)
    }

    override suspend fun getAllByPlanId(planId: Long): List<DayResult> = dayResultLocalDataSource.getAllByPlanId(planId).map { it.toModel() }

    override suspend fun getAllByMonth(startTime: Long, endTime: Long): List<DayResult> = dayResultLocalDataSource.getAllByMonth(startTime, endTime).map { it.toModel() }

    override suspend fun get(id: Long): DayResult = dayResultLocalDataSource.get(id).toModel()

    override suspend fun delete(vararg dayResult: DayResult) {
        val dayResultEntityList = dayResult.map { it.toEntity() }.toTypedArray()
        dayResultLocalDataSource.delete(*dayResultEntityList)
    }

}