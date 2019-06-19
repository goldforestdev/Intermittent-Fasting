package com.goldforest.domain.usercase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.goldforest.domain.model.Plan

interface GetPlanListResultPaging {
    fun get(): LiveData<PagedList<Plan>>
}