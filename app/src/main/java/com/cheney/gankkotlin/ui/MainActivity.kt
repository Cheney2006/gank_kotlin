package com.cheney.gankkotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.base.di.ViewModelFactory
import com.cheney.gankkotlin.databinding.ActivityMainBinding
import com.cheney.gankkotlin.dialog.ProgressFragment
import com.cheney.gankkotlin.util.StatusBarUtil
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val sessionViewModel by viewModels<SessionViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home, R.id.navigation_category, R.id.navigation_girl, R.id.navigation_my -> {
                    binding.navView.visibility = View.VISIBLE
                }
                else -> {
                    binding.navView.visibility = View.GONE
                }
            }

        }


        initProgress()

    }

    private fun initProgress() {
        sessionViewModel.progressLiveData.observe(this) {
            Log.i("Cheney",it.status.name)
            var progressFragment =
                supportFragmentManager.findFragmentByTag(ProgressFragment.TAG)
            progressFragment = progressFragment ?: ProgressFragment(it.message, it.cancelable)
            if (it.isFinished() && progressFragment.isAdded) {
                (progressFragment as ProgressFragment).dismissAllowingStateLoss()
            } else {
                if (progressFragment.isAdded) {
                    (progressFragment as ProgressFragment).dismissAllowingStateLoss()
                }
                (progressFragment as ProgressFragment).show(supportFragmentManager, ProgressFragment.TAG)
            }
        }
    }

}