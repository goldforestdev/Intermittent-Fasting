package com.goldforest.capdiet.view.plan
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import com.goldforest.capdiet.R
import kotlinx.android.synthetic.main.number_picker_dialg.*


class PlanPickerDialog(context: Context)
    : AlertDialog(context), DialogInterface.OnClickListener {

    private var hourNumberPicker: NumberPicker
    private var minNumberPicker : NumberPicker
    init {

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.number_picker_dialg, null)
        setView(view)
        setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.ok),this)
        setButton(DialogInterface.BUTTON_NEGATIVE, context.getString(R.string.cancel), this)


        hourNumberPicker = view.findViewById(R.id.npHour) as NumberPicker
        minNumberPicker = view.findViewById(R.id.npMin) as NumberPicker

        hourNumberPicker.maxValue = 24
        hourNumberPicker.minValue = 16

        minNumberPicker.maxValue = 59
        minNumberPicker.minValue = 0

    }


    override fun onClick(dialog: DialogInterface, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {

            }
            DialogInterface.BUTTON_NEGATIVE -> cancel()
        }
    }







}