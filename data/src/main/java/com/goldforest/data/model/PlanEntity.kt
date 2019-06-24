package com.goldforest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.goldforest.domain.model.Plan
import com.goldforest.domain.model.PlanType

@Entity(tableName = "plan_entity")
data class PlanEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var planName: String,
    var type: Int,
    var startTime: String,
    var endTime: String,
    var day: Int,
    var startDate: String,
    var endDate: String,
    var startDateTime: Long,
    var endDateTime: Long,
    var done: Boolean
)

fun Plan.toEntity() = PlanEntity(id, planName, type.code, startTime, endTime, day, startDate, endDate, startDateTime, endDateTime, done)

fun PlanEntity.toModel() = Plan(id, planName, PlanType.of(type), startTime, endTime, day, startDate, endDate, startDateTime, endDateTime, done)