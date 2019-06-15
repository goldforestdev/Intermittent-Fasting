package com.goldforest.capdiet.view.calendar


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.goldforest.capdiet.R


class CalendarMainFragment : Fragment() {

    private lateinit var calendarPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_calendar_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarPager = view.findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val startIdx = Integer.MAX_VALUE / 2 - 1
        calendarPager.adapter = fragmentManager?.let { CalendarPagerAdapter(it, startIdx) }
        calendarPager.currentItem = startIdx
    }

}
