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

    val HOUR_MIN_NUMER = 16
    val HOUR_MAX_NUMER = 24

    val MINUTE_MIN_NUMER = 0
    val MINUTE_MAX_NUMER = 59

    init {

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.number_picker_dialg, null)
        setView(view)
        setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.ok),this)
        setButton(DialogInterface.BUTTON_NEGATIVE, context.getString(R.string.cancel), this)

        setNumberPickerView(view)

    }

    private fun setNumberPickerView(view: View) {
        hourNumberPicker = view.findViewById(R.id.npHour) as NumberPicker
        minNumberPicker = view.findViewById(R.id.npMin) as NumberPicker

        hourNumberPicker.maxValue = HOUR_MAX_NUMER
        hourNumberPicker.minValue = HOUR_MIN_NUMER

        minNumberPicker.maxValue = MINUTE_MAX_NUMER
        minNumberPicker.minValue = MINUTE_MIN_NUMER
    }


    override fun onClick(dialog: DialogInterface, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {

            }
            DialogInterface.BUTTON_NEGATIVE -> cancel()
        }
    }







}