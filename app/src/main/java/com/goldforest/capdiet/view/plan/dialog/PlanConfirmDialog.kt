package com.goldforest.capdiet.view.plan.dialog


import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.goldforest.capdiet.R
import kotlinx.android.synthetic.main.plan_confirm_dialg.*

import android.os.Environment
import java.io.FileNotFoundException
import java.io.FileOutputStream
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.FileProvider
import com.goldforest.capdiet.BuildConfig
import com.goldforest.capdiet.utils.checkExternalStoragePermission
import java.io.File


class PlanConfirmDialog(
    context: Context,
    private val startDate : String,
    private val endDate : String,
    private val startTime : String,
    private val endTime : String,
    private val attachedCallback: AttachedCallback
) : AlertDialog(context), DialogInterface.OnClickListener {

    interface AttachedCallback {
        fun done(planTitle : String)
    }

    private var planLayout : ConstraintLayout? = null
    private val filePath = Environment.getExternalStorageDirectory().toString() + "/Download/capture.jpg"

    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.plan_confirm_dialg, null)
        setView(view)
        setCancelable(false)
        setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.ok),this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        planLayout = findViewById(R.id.planLayout)

        initView()
    }

    private fun initView() {
        etPlanTitle.setText(getPlanTitle())
        etPlanTitle.setSelection(etPlanTitle.text.length)
        etPlanTitle.requestFocus()
        tvDate.text = getDate()
        tvTime.text = getTime()

        if (!checkExternalStoragePermission(context)) {
            btShare.visibility = View.GONE
        }

        btShare.setOnClickListener {
            capture()
        }
    }

    private fun getTime(): String {
        return "$startTime ~ $endTime"
    }

    private fun getDate() : String{
        return "$startDate ~ $endDate"
    }

    private fun getPlanTitle() : String {
        return  "$startDate ${context.getString(R.string.start)} ${context.getString(R.string.plan)}"
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {
                clickPositiveButton()
            }
        }
    }

    private fun clickPositiveButton() {
        val planTitle = etPlanTitle.text.toString()
        if (planTitle.isEmpty()) {
            Toast.makeText(context, context.getString(R.string.empty_title_notify), Toast.LENGTH_LONG).show()
            return
        }
        fileDelete()
        attachedCallback.done(etPlanTitle.text.toString())
        dismiss()
    }

    private fun capture () {
        planLayout?.apply {
            this.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(this.drawingCache)
            this.isDrawingCacheEnabled = false
            val fos: FileOutputStream
            try {
                fos = FileOutputStream(filePath)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()
                fos.close()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }

       imageFileShare()

    }

    private fun imageFileShare() {
        val file = File(filePath)
        if(file.exists() && file.isFile) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/jpeg"
            val uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file)
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.my_plan_title)))
        }
    }

    private fun fileDelete() {
        try {
            val file = File(filePath)
            if(file.exists() && file.isFile) {
                file.delete()
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }
}