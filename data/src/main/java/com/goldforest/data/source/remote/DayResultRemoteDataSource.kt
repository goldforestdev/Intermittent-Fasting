package com.goldforest.data.source.remote

import com.goldforest.data.model.DayResultEntity
import com.goldforest.data.source.DayResultDataSource

class DayResultRemoteDataSource : DayResultDataSource {
    override suspend fun save(vararg dayResult: DayResultEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAll(planId: Long): Array<DayResultEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun get(id: Long): DayResultEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(vararg dayResult: DayResultEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}