package com.goldforest.capdiet.view.planlist


import android.os.Bundle
import android.view.View
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentPlanListBinding
import com.goldforest.capdiet.viewmodel.PlanListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlanListFragment : BaseFragment<FragmentPlanListBinding, PlanListViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_plan_list
    override val viewModel: PlanListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vm = viewModel
        viewDataBinding.lifecycleOwner = this
        initAdapter()
    }

    private fun initAdapter() {

    }
}
