package com.goldforest.capdiet.view.calendar

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goldforest.capdiet.R
import com.goldforest.capdiet.common.getStatusIcon
import com.goldforest.capdiet.common.month
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.model.DayResultType

class CalendarAdapter(
    private val dateList: MutableList<DayResult> = mutableListOf(),
    private val clickListener: CalendarClickListener
) : RecyclerView.Adapter<CalendarAdapter.CalendarHolder>() {

    interface CalendarClickListener {
        fun onCalendarClick(dayResult: DayResult)
    }

    private var month: Long = 0

    fun setData(baseTime: Long, dateList: List<DayResult>) {
        month = baseTime.month()
        this.dateList.clear()
        this.dateList.addAll(dateList)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarHolder =
        CalendarHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_calendar, parent, false))

    override fun getItemCount(): Int = dateList.size

    override fun onBindViewHolder(holder: CalendarHolder, position: Int) {
        val dayResult = dateList[position]

        holder.tvDate.text = "${dayResult.dayOfMonth}"
        if (dayResult.planId != INVALID_DATA) {
            holder.tvDate.setTypeface(null, Typeface.BOLD)
        }

        if (dayResult.type != DayResultType.NOT_INPUT) {
            holder.ivStatus.setImageResource(dayResult.getStatusIcon())
        }

        holder.itemView.setOnClickListener {
            clickListener.onCalendarClick(dayResult)
        }
    }

    inner class CalendarHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        var ivStatus: ImageView = itemView.findViewById(R.id.ivStatus)
    }

}
