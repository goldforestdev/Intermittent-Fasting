package com.goldforest.data.common

import java.util.*

fun Long.year(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.YEAR)
}

fun Long.month(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.MONTH) + 1
}

fun Long.dayOfMonth(): Int {
    val calendar = Calendar.getInstance().apply { timeInMillis = this@dayOfMonth }
    return calendar.get(Calendar.DAY_OF_MONTH)
}