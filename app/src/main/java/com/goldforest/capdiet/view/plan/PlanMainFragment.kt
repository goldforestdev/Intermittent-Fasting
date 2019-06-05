package com.goldforest.capdiet.view.plan

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentPlanBinding
import com.goldforest.capdiet.viewmodel.PlanViewModel
import com.goldforest.domain.model.PlanType
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class PlanMainFragment : BaseFragment<FragmentPlanBinding, PlanViewModel>(), View.OnClickListener {
    override val layoutResourceId: Int = R.layout.fragment_plan
    override val viewModel: PlanViewModel by viewModel()

    private var currentPlanType : PlanType = PlanType.PLAN_16_8

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.vm = viewModel
        viewDataBinding.lifecycleOwner = this

        initLiveDataObserver()
        initView()
    }

    private fun initView () {
        val calendar = getCalendar()
        viewModel.setStartTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))
        viewModel.setFastingTime(16, 0)

        viewDataBinding.btNextFragment.setOnClickListener(this)
        viewDataBinding.tvStartTime.setOnClickListener(this)
        viewDataBinding.tvFastingTime.setOnClickListener(this)
    }

    private fun initLiveDataObserver() {
        viewModel.planType.observe(this, Observer {
            setPlanTypeView(it)
        })
    }

    private fun showTimePicker () {
        val calendar = getCalendar()
        calendar.timeInMillis = viewModel.startTime.value!!
        val timePickerDialog = TimePickerDialog(activity, R.style.TimePickerTheme, TimePickerDialog.OnTimeSetListener { _, hour, min ->
            viewModel.setStartTime(hour, min)
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),false)
        timePickerDialog.show()
    }

    private fun showNumberPicker () {
        activity?.apply{
            val planPickerDialog = PlanPickerDialog(this,object :PlanPickerDialog.OnNumberSetListener {
                override fun onNumberSet(hourOfDay: Int, minute: Int) {
                    viewModel.setFastingTime(hourOfDay, minute)
                }
            },16,0)
            planPickerDialog.setTitle(R.string.set_intermittent_fast_time)
            planPickerDialog.setMessage("간헐적 단식 시간을 설정 하세요")
            planPickerDialog.show()
        }
    }

    private fun getCalendar() : Calendar {
        return Calendar.getInstance()
    }

    override fun onClick(v: View?) {
        v?.apply {
            when(v.id) {
                R.id.btNextFragment ->  findNavController().navigate(R.id.action_planFragment_to_planTermFragment)
                R.id.tvStartTime -> showTimePicker()
                R.id.tvFastingTime -> showNumberPicker()
            }
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
                viewDataBinding.plan1Layout.visibility = View.VISIBLE
                viewDataBinding.plan2Layout.visibility = View.GONE
            }
            else -> {
                Toast.makeText(activity, "5/2 다이어트 타입을 설정 합니다.", Toast.LENGTH_LONG).show()
                viewDataBinding.plan1Layout.visibility = View.GONE
                viewDataBinding.plan2Layout.visibility = View.VISIBLE
            }
        }
    }
}
