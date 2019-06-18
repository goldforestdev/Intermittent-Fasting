package com.goldforest.capdiet.view.dayResult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.goldforest.capdiet.R
import com.goldforest.domain.model.DayResult
import kotlinx.android.synthetic.main.activity_day_result.*
import org.koin.android.ext.android.inject
import java.util.*

const val DAY_RESULT_Y = "DAY_RESULT_YEAR"
const val DAY_RESULT_M = "DAY_RESULT_MONTH"
const val DAY_RESULT_D = "DAY_RESULT_DAY_OF_MONTH"
const val DAY_RESULT_ID = "DAY_RESULT_ID"

class DayResultActivity : AppCompatActivity(), DayResultContract.View {
    override fun test(s: String) {

    }

    private val TAG = DayResultActivity::class.java.simpleName

    private val presenter: DayResultContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_result)

        presenter.subscribe(this)

        val dayResultId = intent.getLongExtra(DAY_RESULT_ID, -1L)
        val year = intent.getIntExtra(DAY_RESULT_Y, -1)
        val month = intent.getIntExtra(DAY_RESULT_M, -1)
        val dayOfMonth = intent.getIntExtra(DAY_RESULT_D, -1)

        showDateTime(year, month, dayOfMonth)

        Log.d(TAG, "[GF] onViewCreated - dayResultId: $dayResultId")
        if (dayResultId == -1L) {
            showNotEnteredView(true)
        } else {
            presenter.getDayResult(dayResultId)
        }

        presenter.test()
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    private fun showDateTime(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
    }

    private fun showNotEnteredView(show: Boolean) {

    }

    override fun onDayResultLoaded(dayResult: DayResult) {

    }
}
