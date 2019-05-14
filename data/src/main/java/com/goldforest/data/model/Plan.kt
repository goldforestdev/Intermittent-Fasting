package com.goldforest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plan (
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