package com.goldforest.capdiet.view.plan


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentPlanTermBinding
import com.goldforest.capdiet.view.plan.dialog.PlanConfirmDialog
import com.goldforest.capdiet.view.plan.dialog.PlanDatePickerDialog
import com.goldforest.capdiet.viewmodel.PlanViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar


class PlanTermFragment : BaseFragment<FragmentPlanTermBinding, PlanViewModel>(), View.OnClickListener {

    override val layoutResourceId: Int = R.layout.fragment_plan_term
    override val viewModel: PlanViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vm = viewModel
        viewDataBinding.lifecycleOwner = this

        initView()
    }

    private fun initView() {
        viewModel.initPlanDate()
        viewDataBinding.btDoneFragment.setOnClickListener {
            if (!viewModel.validCheckEndDate()) {
                changeWidgetBackgroundColor(viewDataBinding.tvEndDate, R.drawable.round_corner_failure_background)
                Toast.makeText(context, getString(R.string.end_date_setting_invalid), Toast.LENGTH_LONG).show()
            } else {
                activity?.apply{
                    val planPickerDialog = PlanConfirmDialog(this,
                        viewModel.startDateString.value!!,
                        viewModel.endDateString.value!!,
                        viewModel.startTimeViewString.value!!,
                        viewModel.endTimeViewString.value!!,
                        object : PlanConfirmDialog.AttachedCallback {
                            override fun done(planTitle: String) {
                                viewModel.createPlan(planTitle)
                                findNavController().navigate(R.id.action_planTermFragment_to_mainFragment)
                            }
                        })
                    planPickerDialog.show()
                }
            }
        }

        viewDataBinding.tvStartDate.setOnClickListener(this)
        viewDataBinding.tvEndDate.setOnClickListener(this)

        viewModel.termType.observe(this, Observer {
            if (it != PlanViewModel.PlanTermType.PLAN_TERM_USER_SETTING) {
                viewDataBinding.tvEndDate.isEnabled = false
                viewDataBinding.tvEndDate.background = null
            } else {
                viewDataBinding.tvEndDate.isEnabled = true
                changeWidgetBackgroundColor(viewDataBinding.tvEndDate, R.drawable.round_corner_success_background)
            }

        })
    }

    private fun showDatePicker (view: View, calendar: Calendar) {
        val calendarYear = calendar.get(Calendar.YEAR)
        val calendarMonth = calendar.get(Calendar.MONTH)
        val calendarDay = calendar.get(Calendar.DAY_OF_MONTH)

         val datePickerDialog = PlanDatePickerDialog(object :
             PlanDatePickerDialog.OnDateClickListener {
             override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
                 when (view.id) {
                     R.id.tvStartDate -> viewModel.setStartDate(year, month, day)
                     R.id.tvEndDate -> {
                         viewModel.setEndDate(year, month, day)
                         if (!viewModel.validCheckEndDate()) {
                             changeWidgetBackgroundColor(viewDataBinding.tvEndDate, R.drawable.round_corner_failure_background)
                         }
                     }
                 }
             }
         }, calendarYear, calendarMonth, calendarDay)
        datePickerDialog.show(activity!!.supportFragmentManager, "datePicker")
    }


    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.tvStartDate -> showDatePicker(v, viewModel.getDateCalendar(viewModel.startDateString.value))
            R.id.tvEndDate -> {
                if(viewModel.termType.value == PlanViewModel.PlanTermType.PLAN_TERM_USER_SETTING) {
                    changeWidgetBackgroundColor(viewDataBinding.tvEndDate, R.drawable.round_corner_success_background)
                }
                showDatePicker(v,viewModel.getDateCalendar(viewModel.endDateString.value))
            }
        }
    }

    private fun changeWidgetBackgroundColor (view : View, resId : Int) {
        view.background = activity?.getDrawable(resId)
    }

}
