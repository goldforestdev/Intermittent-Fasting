package com.goldforest.domain.model

data class DayResult(
    var id: Long, // Date
    var type: DayResultType,
    var year: Int,
    var month: Int, // 1 ~ 12
    var dayOfMonth: Int,
    var planId: Long // ForeignKey
)

enum class DayResultType(val code: Int) {
    NOT_INPUT(0),
    SUCCESS(1),
    FAILED(2);

    companion object {
        fun of(code: Int): DayResultType = when (code) {
            0 -> NOT_INPUT
            1 -> SUCCESS
            else -> FAILED
        }
    }
}