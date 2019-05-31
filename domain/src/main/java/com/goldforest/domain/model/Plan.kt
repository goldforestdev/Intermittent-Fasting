package com.goldforest.domain.model

data class Plan(
    var id: Long,
    var planName: String,
    var type: PlanType,
    var startTime: String,
    var endTime: String,
    var day: Int,
    var startDate: String,
    var endDate: String,
    var done: Boolean
)

enum class PlanType {
    PLAN_16_8,
    PLAN_5_2
}