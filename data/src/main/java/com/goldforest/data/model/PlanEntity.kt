package com.goldforest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.goldforest.domain.model.Plan
import com.goldforest.domain.model.PlanType

@Entity(tableName = "plan_entity")
data class PlanEntity (
    @PrimaryKey
    var id: Long,
    var planName: String,
    var type: Int,
    var startTime: Long,
    var endTime: Long,
    var day: Int,
    var startDate: String,
    var endDate: String,
    var done: Boolean
)

fun Plan.toEntity() = PlanEntity(id, planName, type.code, startTime, endTime, day, startDate, endDate, done)

fun PlanEntity.toModel() = Plan(id, planName, PlanType.of(type), startTime, endTime, day, startDate, endDate, done)