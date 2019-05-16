package com.goldforest.data.source

import com.goldforest.data.model.DayResultEntity

interface DayResultDataSource {
    suspend fun save(vararg dayResult: DayResultEntity)

    suspend fun getAll(planId: Long): Array<DayResultEntity>

    suspend fun get(id: String): DayResultEntity

    suspend fun delete(vararg dayResult: DayResultEntity)
}