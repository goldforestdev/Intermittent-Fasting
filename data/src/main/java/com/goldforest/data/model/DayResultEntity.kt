package com.goldforest.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.model.DayResultType

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
    var id: Long, // Date
    var type: Int,
    var planId: Long // ForeignKey
)

fun DayResult.toEntity() = DayResultEntity(id, type.code, planId)

fun DayResultEntity.toModel() = DayResult(id, DayResultType.of(type), planId)