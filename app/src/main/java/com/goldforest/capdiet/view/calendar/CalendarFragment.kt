package com.goldforest.capdiet.view.calendar


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goldforest.capdiet.R
import com.goldforest.capdiet.extentions.getDateList
import com.goldforest.domain.model.DayResult
import kotlinx.android.synthetic.main.fragment_calendar.*
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

const val TIME_IN_MILLIS = "timeInMillis"

class CalendarFragment : Fragment(), CalendarContract.View {

    private val TAG = CalendarFragment::class.java.simpleName

    private val presenter: CalendarContract.Presenter by inject()

    private val calendarAdapter: CalendarAdapter by lazy { CalendarAdapter(context!!, gvCalendar) }
    private val dateFormat = SimpleDateFormat("yyyy MMMM")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_calendar, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe(this)

        gvCalendar!!.adapter = calendarAdapter
        arguments?.getLong(TIME_IN_MILLIS)?.let { time ->
            tvMonth.text = dateFormat.format(Date(time))
            calendarAdapter.setData(time, time.getDateList())
            presenter.getDayResults(time)
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun onDayResultsLoaded(dayResults: List<DayResult>) {
        Log.d(TAG, "[GF] onDayResultsLoaded - result: $dayResults")
    }
}
