package com.goldforest.capdiet.view



import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
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
    private lateinit var timePickerView : View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.vm = viewModel

        initLiveDataObserver()
        initView()
    }

    private fun initView () {
        val calendar = getCurrentTime()

        setTimeTextView(viewDataBinding.tvStartTime, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))
        setTimeTextView(viewDataBinding.tvEndTime, calendar.get(Calendar.HOUR_OF_DAY) + 5, calendar.get(Calendar.MINUTE))
    }

    private fun initLiveDataObserver() {
        viewModel.planType.observe(this, Observer {
            setPlanTypeView(it)
        })

        viewModel.timePicker.observe(this, Observer {
            showTimePicker(it)
        })
    }

    private fun showTimePicker (view : View) {
        timePickerView = view

        val timePickerDialog = TimePickerDialog(activity, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            setTimeTextView(view, hourOfDay, minute)
        }, getCurrentTime().get(Calendar.HOUR_OF_DAY),getCurrentTime().get(Calendar.MINUTE),false)
        timePickerDialog.show()
    }

    private fun setTimeTextView(view: View, hourOfDay: Int,  minute: Int) {
        val strAmPm: String
        val hour: Int

        if (hourOfDay < 12) {
            strAmPm = getString(R.string.am)
            hour = hourOfDay
        } else {
            strAmPm = getString(R.string.pm)
            hour = hourOfDay - 12
        }

        when (view.id) {
            R.id.tvStartTime -> {
                viewDataBinding.tvStartTime.text = "$hour : $minute $strAmPm"
            }
            else -> viewDataBinding.tvEndTime.text = "$hour : $minute $strAmPm"
        }
    }

    private fun getCurrentTime() : Calendar {
        return Calendar.getInstance()
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
