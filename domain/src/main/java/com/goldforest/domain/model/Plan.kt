package com.goldforest.domain.model

data class Plan(
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