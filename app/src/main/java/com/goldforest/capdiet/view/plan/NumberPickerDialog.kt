package com.goldforest.capdiet.view.plan
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.goldforest.capdiet.R

class NumberPickerDialog(context: Context)
    : AlertDialog(context) {



    init {

        val themeContext = getContext()
        val inflater = LayoutInflater.from(themeContext)
        val view = inflater.inflate(R.layout.number_picker_dialg, null)
        setView(view)
    }


}