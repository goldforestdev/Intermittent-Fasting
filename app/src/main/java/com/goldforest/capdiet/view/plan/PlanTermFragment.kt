package com.goldforest.capdiet.view.plan


import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentPlanTermBinding
import com.goldforest.capdiet.viewmodel.PlanViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar


class PlanTermFragment : BaseFragment<FragmentPlanTermBinding, PlanViewModel>(), View.OnClickListener {

    override val layoutResourceId: Int = R.layout.fragment_plan_term
    override val viewModel: PlanViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vm = viewModel
        viewDataBinding.lifecycleOwner = this

        initView()
    }

    private fun initView() {
        viewModel.initPlanDate()
        viewDataBinding.btNextFragment.setOnClickListener {
            findNavController().navigate(R.id.action_planTermFragment_to_planNotificationFragment)
        }

        viewDataBinding.tvStartDate.setOnClickListener(this)
        viewDataBinding.tvEndDate.setOnClickListener(this)
    }


    private fun showDatePicker (view: View, calendar: Calendar) {
        val calendarYear = calendar.get(Calendar.YEAR)
        val calendarMonth = calendar.get(Calendar.MONTH)
        val calendarDay = calendar.get(Calendar.DAY_OF_MONTH)

         val datePickerDialog = PlanDatePickerDialog(object:PlanDatePickerDialog.OnDateClickListener{
            override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
                when(view.id) {
                    R.id.tvStartDate -> viewModel.setStartDate(year, month, day)
                    R.id.tvEndDate -> viewModel.setEndDate(year,month,day)
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
