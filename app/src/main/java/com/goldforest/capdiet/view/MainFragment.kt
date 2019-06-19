package com.goldforest.capdiet.view


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentMainBinding
import com.goldforest.capdiet.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_main
    override val viewModel: MainViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).toolbar?.visibility = View.VISIBLE

        fl_plan.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_planFragment)
        }

        fl_calendar.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_calendarFragment)
        }

        fl_list.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_planListFragment)
        }
    }
}
