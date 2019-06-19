package com.goldforest.capdiet.view.planlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goldforest.capdiet.R
import com.goldforest.domain.model.Plan

class PlanListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val month: ImageView = view.findViewById(R.id.iv_month)
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
        period.text = String.format("%s~%s", plan.startDate, plan.endDate)
    }

    companion object {
        fun create(parent: ViewGroup): PlanListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.plan_list_item, parent, false)
            return PlanListViewHolder(view)
        }
    }
}
