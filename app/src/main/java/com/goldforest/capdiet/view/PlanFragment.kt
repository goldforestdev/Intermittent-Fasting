package com.goldforest.capdiet.view



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentPlanBinding
import com.goldforest.capdiet.viewmodel.PlanType
import com.goldforest.capdiet.viewmodel.PlanViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlanFragment : BaseFragment<FragmentPlanBinding, PlanViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_plan
    override val viewModel: PlanViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vm = viewModel

        activity?.let {fragmentActivity ->
            viewModel.planType.observe(
                fragmentActivity,
                Observer { planType ->
                    setPlanTypeView(planType)
                })
        }
    }


    private fun setPlanTypeView (planType: PlanType) {
        when (planType) {

            //각 PlanType 별로 시간 or 요일 설정으로 변경
            PlanType.PLAN_16_8 -> Toast.makeText(activity, "16/8 타입의 다이어트 계획을 설정 합니다.", Toast.LENGTH_LONG).show()
            else -> Toast.makeText(activity, "5/2 타입의 다이어트 계획을 설정 합니다.", Toast.LENGTH_LONG).show()
        }
    }
}
