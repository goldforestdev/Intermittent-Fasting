package com.goldforest.capdiet.view.dayResult

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.goldforest.capdiet.R
import com.goldforest.capdiet.common.getDayOfMonthWithDayOfWeek
import com.goldforest.capdiet.common.getYearMonth
import com.goldforest.capdiet.view.calendar.INVALID_DATA
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.model.DayResultType
import kotlinx.android.synthetic.main.activity_day_result.*
import org.koin.android.ext.android.inject
import java.util.*

const val DAY_RESULT_Y = "DAY_RESULT_YEAR"
const val DAY_RESULT_M = "DAY_RESULT_MONTH"
const val DAY_RESULT_D = "DAY_RESULT_DAY_OF_MONTH"
const val DAY_RESULT_ID = "DAY_RESULT_ID"
const val DAY_RESULT_PLAN_ID = "DAY_RESULT_PLAN_ID"
const val DAY_RESULT_TYPE = "DAY_RESULT_TYPE"

class DayResultActivity : AppCompatActivity(), DayResultContract.View {

    private val TAG = DayResultActivity::class.java.simpleName

    private val presenter: DayResultContract.Presenter by inject()

    private var dayResultId: Long = -1L
    private var year: Int = -1
    private var month: Int = -1
    private var dayOfMonth: Int = -1
    private var planId: Long = -1
    private var type: DayResultType = DayResultType.NOT_INPUT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_result)

        presenter.subscribe(this)

        setSupportActionBar(toolbar)
        supportActionBar?.let { actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
        }
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        dayResultId = intent.getLongExtra(DAY_RESULT_ID, -1L)
        year = intent.getIntExtra(DAY_RESULT_Y, -1)
        month = intent.getIntExtra(DAY_RESULT_M, -1)
        dayOfMonth = intent.getIntExtra(DAY_RESULT_D, -1)
        planId = intent.getLongExtra(DAY_RESULT_PLAN_ID, -1L)
        type = intent.getSerializableExtra(DAY_RESULT_TYPE) as DayResultType

        showDateTime()

        Log.d(TAG, "[GF] onViewCreated - dayResultId: $dayResultId")

        if (type == DayResultType.NOT_INPUT) {
            showEditView(true)
        } else {
            presenter.getDayResult(dayResultId)
        }

        btnSave.setOnClickListener { saveDayResult() }
    }

    private fun saveDayResult() {
        Log.d(TAG, "[GF] saveDayResult")
        val id = if (dayResultId == INVALID_DATA) System.currentTimeMillis() else dayResultId
        val type = if (rBtnSuccess.isChecked) DayResultType.SUCCESS else DayResultType.FAILED
        val dayResult = DayResult(
            id = id,
            year = year,
            month = month,
            dayOfMonth = dayOfMonth,
            type = type,
            planId = planId
        )
        presenter.save(dayResult)
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    private fun showDateTime() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        tvYearMonth.text = calendar.getYearMonth()
        tvDayOfMonth.text = calendar.getDayOfMonthWithDayOfWeek()
    }

    private fun showEditView(show: Boolean) {
        if (show) {
            groupFailure.visibility = View.GONE
            groupSuccess.visibility = View.GONE
            groupEditView.visibility = View.VISIBLE
        } else {
            groupEditView.visibility = View.GONE
        }
    }

    override fun onDayResultLoaded(dayResult: DayResult) {
        showEditView(false)
        if (dayResult.type == DayResultType.SUCCESS) {
            groupSuccess.visibility = View.VISIBLE
        } else {
            groupFailure.visibility = View.VISIBLE
        }
    }

}
