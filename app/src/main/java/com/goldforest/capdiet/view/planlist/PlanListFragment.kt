package com.goldforest.capdiet.view.planlist


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentPlanListBinding
import com.goldforest.capdiet.viewmodel.PlanListViewModel
import com.goldforest.domain.model.Plan
import kotlinx.android.synthetic.main.fragment_plan_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlanListFragment : BaseFragment<FragmentPlanListBinding, PlanListViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_plan_list
    override val viewModel: PlanListViewModel by viewModel()
    private val adapter = PlanListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vm = viewModel
        viewDataBinding.lifecycleOwner = this
        initAdapter()
    }

    private fun initAdapter() {
        rv_plan_list.adapter = adapter
        viewModel.planList.observe(this, Observer<PagedList<Plan>> {
            Log.d("Activity", "list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            rv_plan_list.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            rv_plan_list.visibility = View.VISIBLE
        }
    }
}
