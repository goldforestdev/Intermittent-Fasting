package com.goldforest.capdiet.view.plan
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import com.goldforest.capdiet.R


class PlanPickerDialog(context: Context)
    : AlertDialog(context), DialogInterface.OnClickListener {

    private var hourNumberPicker: NumberPicker
    private var minNumberPicker : NumberPicker

    companion object {
        const val HOUR_MIN_NUMBER = 16
        const val HOUR_MAX_NUMBER = 24

        const val MINUTE_MIN_NUMBER = 0
        const val MINUTE_MAX_NUMBER = 59
    }

    init {

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.number_picker_dialg, null)
        setView(view)
        setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.ok),this)
        setButton(DialogInterface.BUTTON_NEGATIVE, context.getString(R.string.cancel), this)
        hourNumberPicker = view.findViewById(R.id.npHour) as NumberPicker
        minNumberPicker = view.findViewById(R.id.npMin) as NumberPicker

        setNumberPickerView()

    }

    private fun setNumberPickerView() {
        hourNumberPicker.maxValue = HOUR_MAX_NUMBER
        hourNumberPicker.minValue = HOUR_MIN_NUMBER

        minNumberPicker.maxValue = MINUTE_MAX_NUMBER
        minNumberPicker.minValue = MINUTE_MIN_NUMBER
    }


    override fun onClick(dialog: DialogInterface, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {

            }
            DialogInterface.BUTTON_NEGATIVE -> cancel()
        }
    }

}