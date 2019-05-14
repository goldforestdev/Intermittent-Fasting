package com.goldforest.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Plan::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("planId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class DayResult(
    @PrimaryKey
    var id: String, // Date
    var isSuccess: Int,
    var planId: Long // ForeignKey
)