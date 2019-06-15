package com.goldforest.capdiet.view.dayResult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.goldforest.capdiet.R
import com.goldforest.domain.model.DayResult
import org.koin.android.ext.android.inject

const val DAY_RESULT_ID = "DAY_RESULT_ID "

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
        Log.d(TAG, "[GF] onViewCreated - dayResultId: $dayResultId")
        if (dayResultId != -1L) {
            presenter.getDayResult(dayResultId)
        }

        presenter.test()
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun onDayResultLoaded(dayResult: DayResult) {

    }
}
