package com.goldforest.capdiet.view.plan


import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentPlanTermBinding
import com.goldforest.capdiet.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_plan_term.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar


class PlanTermFragment : BaseFragment<FragmentPlanTermBinding, PlanViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_plan_term
    override val viewModel: PlanViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vm = viewModel
        viewDataBinding.lifecycleOwner = this

        viewModel.initPlanDate()
        initLiveDataObserver()

        btNextFragment.setOnClickListener {
            findNavController().navigate(R.id.action_planTermFragment_to_planNotificationFragment)
        }
    }

    private fun initLiveDataObserver() {
        viewModel.setDateValue.observe(this, Observer {
            setDateView(it)
        })
    }

    private fun setDateView(it: View?) {
        if (it != null) {
            when (it.id) {
                R.id.tvStartDate -> {
                    showDatePicker()
                }
                R.id.tvEndDate -> {
                    showDatePicker()
                }
            }
        }
    }

    private fun showDatePicker () {
        val c = Calendar.getInstance()
        val calendarYear = c.get(Calendar.YEAR)
        val calendarMonth = c.get(Calendar.MONTH)
        val calendarDay = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = PlanDatePickerDialog(object:PlanDatePickerDialog.OnDateClickListener{
            override fun onDateSet(datePicker: DatePicker, i: Int, i1: Int, i2: Int) {

            }
        }, calendarYear, calendarMonth, calendarDay)
        datePickerDialog.show(activity!!.supportFragmentManager, "datePicker")
    }

}
