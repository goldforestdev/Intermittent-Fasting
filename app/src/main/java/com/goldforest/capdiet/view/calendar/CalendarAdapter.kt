package com.goldforest.capdiet.view.calendar

import android.content.Context
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
    private val dateList: MutableList<DayResult> = mutableListOf()
) : RecyclerView.Adapter<CalendarAdapter.CalendarHolder>() {

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
        val date = dateList[position]

        holder.tvDate.text = "${date.dayOfMonth}"

        if (date.type != DayResultType.NOT_INPUT) {
            holder.ivStatus.setImageResource(date.getStatusIcon())
        }
    }

    inner class CalendarHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        var ivStatus: ImageView = itemView.findViewById(R.id.ivStatus)
    }

}
