package com.goldforest.capdiet.view


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseFragment
import com.goldforest.capdiet.databinding.FragmentMainBinding
import com.goldforest.capdiet.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_main
    override val viewModel: MainViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {

    }

}
