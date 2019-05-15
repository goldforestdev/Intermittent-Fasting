package com.goldforest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.goldforest.domain.model.Plan

@Entity(tableName = "plan_entity")
data class PlanEntity (
    @PrimaryKey
    var id: Long,
    var planName: String,
    var type: Int,
    var startTime: String,
    var endTime: String,
    var day: Int,
    var startDate: String,
    var endDate: String,
    var done: Boolean
)

fun Plan.toEntity() = PlanEntity(id, planName, type, startTime, endTime, day, startDate, endDate, done)