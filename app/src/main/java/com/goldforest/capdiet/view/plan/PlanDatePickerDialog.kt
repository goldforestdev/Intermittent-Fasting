package com.goldforest.capdiet.view.plan

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.widget.DatePicker
import android.app.DatePickerDialog
import com.goldforest.capdiet.R
import java.util.*


class PlanDatePickerDialog(private val onDateClickListener: OnDateClickListener?,
                           private val year : Int,
                           private val month : Int,
                           private val dayOfMonth : Int)
    : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {




        return DatePickerDialog(activity!!, R.style.TimePickerTheme,
            DatePickerDialog.OnDateSetListener { datePicker, selectedYear, selectedMonth, selectedDayOfMonth ->
                onDateClickListener?.onDateSet(
                    datePicker,
                    selectedYear,
                    selectedMonth,
                    selectedDayOfMonth
                )
            }, year, month, dayOfMonth
        )
    }
    interface OnDateClickListener {
        fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int)
    }
}

