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

    override suspend fun getAll(planId: Long): Array<DayResult> = dayResultLocalDataSource.getAll(planId).map { it.toModel() }.toTypedArray()

    override suspend fun get(id: String): DayResult = dayResultLocalDataSource.get(id).toModel()

    override suspend fun delete(vararg dayResult: DayResult) {
        val dayResultEntityList = dayResult.map { it.toEntity() }.toTypedArray()
        dayResultLocalDataSource.delete(*dayResultEntityList)
    }

}