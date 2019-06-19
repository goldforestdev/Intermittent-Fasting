package com.goldforest.capdiet.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.goldforest.capdiet.base.BaseViewModel
import com.goldforest.domain.model.Plan
import com.goldforest.domain.usercase.GetPlanListResultPaging

class PlanListViewModel(private val getPlanListResultPaging: GetPlanListResultPaging) : BaseViewModel() {
    val planList: LiveData<PagedList<Plan>> = getPlanListResultPaging.get()
}