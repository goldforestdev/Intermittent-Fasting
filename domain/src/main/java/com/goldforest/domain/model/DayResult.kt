package com.goldforest.domain.model

data class DayResult(
    var id: String, // Date
    var isSuccess: Int,
    var planId: Long // ForeignKey
)