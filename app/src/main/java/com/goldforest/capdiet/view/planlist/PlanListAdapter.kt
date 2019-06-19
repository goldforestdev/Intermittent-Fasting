package com.goldforest.capdiet.view.planlist

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goldforest.domain.model.Plan

class PlanListAdapter : PagedListAdapter<Plan, RecyclerView.ViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlanListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plan = getItem(position)
        if(plan != null) {
            (holder as PlanListViewHolder).bind(plan)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Plan>() {
            override fun areItemsTheSame(oldItem: Plan, newItem: Plan): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Plan, newItem: Plan): Boolean =
                oldItem == newItem
        }

    }

}