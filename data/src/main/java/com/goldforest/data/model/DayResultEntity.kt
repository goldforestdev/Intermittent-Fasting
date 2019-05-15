package com.goldforest.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.goldforest.domain.model.DayResult

@Entity(
    tableName = "day_result_entity",
    foreignKeys = [ForeignKey(
        entity = PlanEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("planId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class DayResultEntity(
    @PrimaryKey
    var id: String, // Date
    var isSuccess: Int,
    var planId: Long // ForeignKey
)

fun DayResult.toEntity() = DayResultEntity(id, isSuccess, planId)