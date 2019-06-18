package com.goldforest.capdiet.view.calendar


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.goldforest.capdiet.R
import com.goldforest.capdiet.view.dayResult.*
import com.goldforest.domain.model.DayResult
import kotlinx.android.synthetic.main.fragment_calendar.*
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*


const val TIME_IN_MILLIS = "timeInMillis"

class CalendarFragment : Fragment(), CalendarContract.View, CalendarAdapter.CalendarClickListener {

    private val TAG = CalendarFragment::class.java.simpleName

    private val presenter: CalendarContract.Presenter by inject()

    private val calendarAdapter: CalendarAdapter by lazy { CalendarAdapter(clickListener = this) }
    private val dateFormat = SimpleDateFormat("yyyy MMMM")
    private var time: Long = System.currentTimeMillis()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_calendar, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCalendar.layoutManager = GridLayoutManager(context, 7)
        rvCalendar.adapter = calendarAdapter
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "[GF] onStart")

        presenter.subscribe(this)
        time = arguments?.getLong(TIME_IN_MILLIS)?:System.currentTimeMillis()

        tvMonth.text = dateFormat.format(Date(time))
        presenter.getDayResults(time)
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun onDayResultsLoaded(dayResults: List<DayResult>) {
        Log.d(TAG, "[GF] onDayResultsLoaded - result: $dayResults")
        calendarAdapter.setData(time, dayResults)
    }

    override fun onCalendarClick(dayResult: DayResult) {
        if (dayResult.planId == INVALID_DATA) {
            presenter.findPlanId(dayResult)
            return
        }

        moveToDayResultView(dayResult)
    }

    override fun onPlanIdFound(dayResult: DayResult) {
        if (dayResult.planId == INVALID_DATA) {
            Toast.makeText(context, R.string.invalid_plan, Toast.LENGTH_SHORT).show()
            return
        }

        moveToDayResultView(dayResult)
    }

    private fun moveToDayResultView(dayResult: DayResult) {
        val intent = Intent(context, DayResultActivity::class.java)
            .putExtra(DAY_RESULT_ID, dayResult.id)
            .putExtra(DAY_RESULT_Y, dayResult.year)
            .putExtra(DAY_RESULT_M, dayResult.month)
            .putExtra(DAY_RESULT_D, dayResult.dayOfMonth)
        startActivity(intent)
    }
}
