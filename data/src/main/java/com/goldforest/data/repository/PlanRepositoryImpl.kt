package com.goldforest.data.repository

import com.goldforest.data.model.toEntity
import com.goldforest.data.model.toModel
import com.goldforest.data.source.PlanDataSource
import com.goldforest.domain.exceptions.NotExistPlanException
import com.goldforest.domain.model.Plan
import com.goldforest.domain.repository.PlanRepository

class PlanRepositoryImpl(
    private val planLocalDataSource: PlanDataSource,
    private val planRemoteDataSource: PlanDataSource
): PlanRepository {

    override suspend fun save(vararg plan: Plan) {
        val planEntityList = plan.map { it.toEntity() }.toTypedArray()
        planLocalDataSource.save(*planEntityList)
        planRemoteDataSource.save(*planEntityList)
    }

    override suspend fun getAll(): List<Plan> = planLocalDataSource.getAll().map { it.toModel() }

    override suspend fun get(id: Long): Plan = planLocalDataSource.get(id).toModel()

    override suspend fun delete(vararg plan: Plan) {
        val planEntityList = plan.map { it.toEntity() }.toTypedArray()
        planLocalDataSource.delete(*planEntityList)
        planRemoteDataSource.delete(*planEntityList)
    }

    override suspend fun getActivePlan(): Plan {
        return planLocalDataSource.getActivePlan()?.toModel() ?: throw NotExistPlanException()
    }

}