package com.goldforest.capdiet.view



import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentPlanBinding
import com.goldforest.capdiet.viewmodel.PlanType
import com.goldforest.capdiet.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_plan.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlanFragment : BaseFragment<FragmentPlanBinding, PlanViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_plan
    override val viewModel: PlanViewModel by viewModel()
    private var currentPlanType : PlanType = PlanType.PLAN_16_8

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.vm = viewModel

        initLiveDataObserver()
    }

    private fun initLiveDataObserver() {
        activity?.let { fragmentActivity ->
            viewModel.planType.observe(
                fragmentActivity,
                Observer { planType ->
                    setPlanTypeView(planType)
                })
        }
    }

    private fun setPlanTypeView (planType: PlanType) {
        if (currentPlanType == planType) {
            return
        } else {
            currentPlanType = planType
        }

        when (currentPlanType) {
            PlanType.PLAN_16_8 -> {
                Toast.makeText(activity, "16/8 다이어트 타입을 설정 합니다.", Toast.LENGTH_LONG).show()
                plan1Layout.visibility = View.VISIBLE
                plan2Layout.visibility = View.GONE
            }
            else -> {
                Toast.makeText(activity, "5/2 다이어트 타입을 설정 합니다.", Toast.LENGTH_LONG).show()
                plan1Layout.visibility = View.GONE
                plan2Layout.visibility = View.VISIBLE
            }
        }
    }
}
