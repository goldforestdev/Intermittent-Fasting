package com.goldforest.capdiet.common

import com.goldforest.capdiet.R
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.model.DayResultType
import java.util.*

fun Long.month(): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.MONTH).toLong()
}

fun Long.getDayOfMonth(): String {
    val calendar = Calendar.getInstance().apply { timeInMillis = this@getDayOfMonth }
    return calendar.get(Calendar.DAY_OF_MONTH).toString()
}

fun Long.getDateList(): List<Long> {
    val months = ArrayList<Long>()
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.set(Calendar.DATE, 1)
    calendar.set(Calendar.HOUR_OF_DAY, 6)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> calendar.add(Calendar.DATE, -1)
        Calendar.TUESDAY -> calendar.add(Calendar.DATE, -2)
        Calendar.WEDNESDAY -> calendar.add(Calendar.DATE, -3)
        Calendar.THURSDAY -> calendar.add(Calendar.DATE, -4)
        Calendar.FRIDAY -> calendar.add(Calendar.DATE, -5)
        Calendar.SATURDAY -> calendar.add(Calendar.DATE, -6)
        else -> {
        }
    }

    for (i in 0..41) {
        months.add(calendar.timeInMillis)
        calendar.add(Calendar.DATE, 1)
    }

    return months
}

fun Long.getStartEndTimes(): Pair<Long, Long> {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    calendar.set(Calendar.HOUR, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    val startTime = calendar.timeInMillis

    calendar.add(Calendar.MONTH, 1)
    val endTime = calendar.timeInMillis

    return startTime to endTime
}

fun Long.get42Days(): List<Long> {
    val months = ArrayList<Long>()
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.set(Calendar.DATE, 1)
    calendar.set(Calendar.HOUR_OF_DAY, 6)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> calendar.add(Calendar.DATE, -1)
        Calendar.TUESDAY -> calendar.add(Calendar.DATE, -2)
        Calendar.WEDNESDAY -> calendar.add(Calendar.DATE, -3)
        Calendar.THURSDAY -> calendar.add(Calendar.DATE, -4)
        Calendar.FRIDAY -> calendar.add(Calendar.DATE, -5)
        Calendar.SATURDAY -> calendar.add(Calendar.DATE, -6)
        else -> {
        }
    }

    for (i in 0..41) {
        months.add(calendar.timeInMillis)
        calendar.add(Calendar.DATE, 1)
    }

    return months
}

fun Calendar.year(): Int = get(Calendar.YEAR)

fun Calendar.month(): Int = get(Calendar.MONTH) + 1

fun Calendar.dayOfMonth(): Int = get(Calendar.DAY_OF_MONTH)

fun DayResult.getStatusIcon() = when (type) {
    DayResultType.SUCCESS -> R.drawable.ic_check
    DayResultType.FAILED -> R.drawable.ic_clear
    else -> -1
}