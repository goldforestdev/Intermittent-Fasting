package com.goldforest.capdiet.view.plan

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.widget.DatePicker
import android.app.DatePickerDialog
import com.goldforest.capdiet.R
import java.util.*


class PlanDatePickerDialog(private val onDateClickListener: OnDateClickListener?)
    : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val calendarYear = c.get(Calendar.YEAR)
        val calendarMonth = c.get(Calendar.MONTH)
        val calendarDay = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity!!, R.style.TimePickerTheme,
            DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
                onDateClickListener?.onDateSet(
                    datePicker,
                    year,
                    month,
                    dayOfMonth
                )
            }, calendarYear, calendarMonth, calendarDay
        )
    }
    interface OnDateClickListener {
        fun onDateSet(datePicker: DatePicker, i: Int, i1: Int, i2: Int)
    }
}

