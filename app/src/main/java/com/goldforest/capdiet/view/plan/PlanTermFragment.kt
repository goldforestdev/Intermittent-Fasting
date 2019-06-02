package com.goldforest.capdiet.view.plan


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentPlanTermBinding
import com.goldforest.capdiet.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_plan_term.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlanTermFragment : BaseFragment<FragmentPlanTermBinding, PlanViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_plan_term
    override val viewModel: PlanViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btNextFragment.setOnClickListener {
            findNavController().navigate(R.id.action_planTermFragment_to_planNotificationFragment)
        }

    }

}
