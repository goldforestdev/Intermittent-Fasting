package com.goldforest.data.common

import java.util.*

fun Long.month(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.MONTH)
}

fun Long.dayOfMonth(): Int {
    val calendar = Calendar.getInstance().apply { timeInMillis = this@dayOfMonth }
    return calendar.get(Calendar.DAY_OF_MONTH)
}