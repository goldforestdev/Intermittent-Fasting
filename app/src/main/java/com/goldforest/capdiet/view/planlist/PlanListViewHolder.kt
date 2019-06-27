package com.goldforest.capdiet.view.planlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goldforest.capdiet.R
import com.goldforest.capdiet.common.getAbbreviationMonth
import com.goldforest.capdiet.common.month
import com.goldforest.domain.model.Plan
import java.util.Calendar.*

class PlanListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val month: TextView = view.findViewById(R.id.tv_month)
    private val name: TextView = view.findViewById(R.id.tv_plan_name)
    private val period: TextView = view.findViewById(R.id.tv_plan_period)

    private var plan: Plan? = null

    fun bind(plan: Plan?) {
        if (plan != null) {
            showPlanData(plan)
        }
    }

    private fun showPlanData(plan: Plan) {
        this.plan = plan
        name.text = plan.planName
        period.text = String.format("%s~%s", plan.startDate, plan.endDate)
        month.text = plan.startDateTime.getAbbreviationMonth()
        setMonthBackground(plan)
    }

    private fun setMonthBackground(plan: Plan) {
        val resources = itemView.resources
        when (plan.startDateTime.month().toInt()) {
            JANUARY -> {
                month.background = resources.getDrawable(R.drawable.circle_jan, null)
            }
            FEBRUARY -> {
                month.background = resources.getDrawable(R.drawable.circle_feb, null)
            }
            MARCH -> {
                month.background = resources.getDrawable(R.drawable.circle_mar, null)
            }
            APRIL -> {
                month.background = resources.getDrawable(R.drawable.circle_apr, null)
            }
            MAY -> {
                month.background = resources.getDrawable(R.drawable.circle_may, null)
            }
            JUNE -> {
                month.background = resources.getDrawable(R.drawable.circle_jun, null)
            }
            JULY -> {
                month.background = resources.getDrawable(R.drawable.circle_jul, null)
            }
            AUGUST -> {
                month.background = resources.getDrawable(R.drawable.circle_aug, null)
            }
            SEPTEMBER -> {
                month.background = resources.getDrawable(R.drawable.circle_sep, null)
            }
            OCTOBER -> {
                month.background = resources.getDrawable(R.drawable.circle_oct, null)
            }
            NOVEMBER -> {
                month.background = resources.getDrawable(R.drawable.circle_nov, null)
            }
            DECEMBER -> {
                month.background = resources.getDrawable(R.drawable.circle_dec, null)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): PlanListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.plan_list_item, parent, false)
            return PlanListViewHolder(view)
        }
    }
}
