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
    var startDateTime: Long,
    var endDateTime: Long,
    var done: Boolean
)

enum class PlanType(val code: Int) {
    PLAN_16_8(0),
    PLAN_5_2(1);

    companion object {
        fun of(code: Int): PlanType = when (code) {
            0 -> PLAN_16_8
            else -> PLAN_5_2
        }
    }
}