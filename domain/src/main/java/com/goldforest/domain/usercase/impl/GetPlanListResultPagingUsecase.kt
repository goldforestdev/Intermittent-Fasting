package com.goldforest.domain.usercase.impl

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.goldforest.domain.model.Plan
import com.goldforest.domain.repository.PlanRepository
import com.goldforest.domain.usercase.GetPlanListResultPaging

class GetPlanListResultPagingUsecase(private val planRepository: PlanRepository) : GetPlanListResultPaging {
    override fun get(): LiveData<PagedList<Plan>> = planRepository.getAllByIdDescPaging()
}