package com.goldforest.data.repository

import com.goldforest.data.model.toEntity
import com.goldforest.data.source.PlanDataSource
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

    override suspend fun getAll(): Array<Plan> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun get(id: Long): Plan {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(vararg plan: Plan) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}