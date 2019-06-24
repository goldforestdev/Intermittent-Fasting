package com.goldforest.data.source.remote

import androidx.paging.DataSource
import com.goldforest.data.model.PlanEntity
import com.goldforest.data.source.PlanDataSource

class PlanRemoteDataSource: PlanDataSource {

    override suspend fun save(vararg plan: PlanEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAll(): Array<PlanEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun get(id: Long): PlanEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(vararg plan: PlanEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getActivePlan(): PlanEntity? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllByIdDesc(): DataSource.Factory<Int, PlanEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}