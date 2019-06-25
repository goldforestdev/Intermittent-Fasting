package com.goldforest.capdiet.view.planlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goldforest.capdiet.R
import com.goldforest.capdiet.common.month
import com.goldforest.domain.model.Plan
import java.util.Calendar.*

class PlanListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val month: TextView = view.findViewById(R.id.tv_month)
    private val name: TextView = view.findViewById(R.id.tv_plan_name)
    private val period: TextView = view.findViewById(R.id.tv_plan_period)

    private var plan: Plan? = null

    fun bind(plan: Plan?) {
        if (plan == null) {

        } else {
            showPlanData(plan)
        }
    }

    private fun showPlanData(plan: Plan) {
        this.plan = plan
        name.text = plan.planName
        period.text = String.format("%s~%s", plan.startDate, plan.endDate)
        showStartMonth(plan)
    }

    private fun showStartMonth(plan: Plan) {
        val resources = itemView.resources
        when (plan.startDateTime.month()) {
            JANUARY.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_jan, null)
                month.text = resources.getString(R.string.january)
            }
            FEBRUARY.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_feb, null)
                month.text = resources.getString(R.string.february)
            }
            MARCH.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_mar, null)
                month.text = resources.getString(R.string.march)
            }
            APRIL.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_apr, null)
                month.text = resources.getString(R.string.april)
            }
            MAY.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_may, null)
                month.text = resources.getString(R.string.may)
            }
            JUNE.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_jun, null)
                month.text = resources.getString(R.string.june)
            }
            JULY.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_jul, null)
                month.text = resources.getString(R.string.july)
            }
            AUGUST.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_aug, null)
                month.text = resources.getString(R.string.august)
            }
            SEPTEMBER.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_sep, null)
                month.text = resources.getString(R.string.september)
            }
            OCTOBER.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_oct, null)
                month.text = resources.getString(R.string.october)
            }
            NOVEMBER.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_nov, null)
                month.text = resources.getString(R.string.november)
            }
            DECEMBER.toLong() -> {
                month.background = resources.getDrawable(R.drawable.circle_dec, null)
                month.text = resources.getString(R.string.december)
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
