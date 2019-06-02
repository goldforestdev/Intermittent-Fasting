package com.goldforest.capdiet.view.plan


import android.os.Bundle
import android.view.View
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentPlanTermBinding
import com.goldforest.capdiet.viewmodel.PlanViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlanTermFragment : BaseFragment<FragmentPlanTermBinding, PlanViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_plan_term
    override val viewModel: PlanViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
