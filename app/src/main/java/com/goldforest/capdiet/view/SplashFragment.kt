package com.goldforest.capdiet.view


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.goldforest.capdiet.R
import com.goldforest.capdiet.utils.checkExternalStoragePermission
import kotlinx.android.synthetic.main.activity_main.*



class SplashFragment : Fragment() {

    companion object {
        const val REQUEST_PERMISSION = 1
    }

    override fun onStart() {
        super.onStart()

        if (checkExternalStoragePermission(activity!!)) {
            showNextFragment()
        } else {
            makeRequest()
        }
    }

    private fun showNextFragment() {
        Handler().postDelayed({
            context?.let {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }
        }, 2000)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).toolbar?.visibility = View.GONE
    }

    private fun makeRequest() {
        activity?.apply {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "데이터 저장을 위해 저장소의 권힌이 필요 합니다.", Toast.LENGTH_LONG).show()
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION)
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION)
                }
            }
        }
    }

}
