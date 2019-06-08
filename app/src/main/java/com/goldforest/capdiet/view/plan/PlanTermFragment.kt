package com.goldforest.capdiet.view.plan


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
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
            activity?.apply{
                Log.e("TAG","[HJ] StartTime : ${viewModel.startTimeViewString.value}")
                val planPickerDialog = PlanConfirmDialog(this,
                    viewModel.startDateString.value!!,
                    viewModel.endDateString.value!!,
                    viewModel.startTimeViewString.value!!,
                    viewModel.endTimeViewString.value!!,
                    object : PlanConfirmDialog.AttachedCallback {
                        override fun done() {

                        }
                    })
                planPickerDialog.setTitle(R.string.my_plan_title)
                planPickerDialog.show()
            }
        }

        viewDataBinding.tvStartDate.setOnClickListener(this)
        viewDataBinding.tvEndDate.setOnClickListener(this)
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
                     R.id.tvEndDate -> viewModel.setEndDate(year, month, day)
                 }
             }
         }, calendarYear, calendarMonth, calendarDay)
        datePickerDialog.show(activity!!.supportFragmentManager, "datePicker")
    }


    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.tvStartDate -> showDatePicker(v, viewModel.getDateCalendar(viewModel.startDateString.value))
            R.id.tvEndDate -> showDatePicker(v,viewModel.getDateCalendar(viewModel.endDateString.value))
        }
    }

}
