package com.goldforest.capdiet.view



import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.Observer
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentPlanBinding
import com.goldforest.capdiet.viewmodel.PlanType
import com.goldforest.capdiet.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_plan.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class PlanFragment : BaseFragment<FragmentPlanBinding, PlanViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_plan
    override val viewModel: PlanViewModel by viewModel()
    private var currentPlanType : PlanType = PlanType.PLAN_16_8

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.vm = viewModel
        initView()
        initLiveDataObserver()
    }

    private fun initView () {
        val calendar = getCurrentTime()
        val am_pm  = when(calendar.get(Calendar.AM_PM)) {
            Calendar.AM -> "오전"
            else -> "오후"
        }

        viewDataBinding.tvStartTime.text = "${getCurrentTime().get(Calendar.HOUR_OF_DAY)} 시 ${getCurrentTime().get(Calendar.MINUTE)} 분 $am_pm"
        viewDataBinding.tvEndTime.text = "${getCurrentTime().get(Calendar.HOUR_OF_DAY) + 3} 시 ${getCurrentTime().get(Calendar.MINUTE)} 분 $am_pm"
    }

    private fun initLiveDataObserver() {
        viewModel.planType.observe(this, Observer {
            setPlanTypeView(it)
        })

        viewModel.timePicker.observe(this, Observer {
            showTimePicker()
        })
    }

    private fun showTimePicker () {
        val timePickerDialog = TimePickerDialog(activity, TimePickerListener,getCurrentTime().get(Calendar.HOUR_OF_DAY),getCurrentTime().get(Calendar.MINUTE),false)
        timePickerDialog.show()
    }

    private fun getCurrentTime() : Calendar {
        return Calendar.getInstance()
    }

    object TimePickerListener : TimePickerDialog.OnTimeSetListener {
        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
            Toast.makeText(view!!.context, "$hourOfDay 시 $minute 시",Toast.LENGTH_LONG).show()
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
