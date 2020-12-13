package com.stackapp.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.stackapp.R
import com.stackapp.constants.AppConstants
import com.stackapp.databinding.ActivityMainBinding
import com.stackapp.view.fragments.FirstFragment
import com.stackapp.view.fragments.SecondFragment
import com.stackapp.view.fragments.ThirdFragment
import com.stackapp.view.interfaces.FragmentListener

class MainActivity : AppCompatActivity(), FragmentListener {

    private lateinit var binding: ActivityMainBinding

    private var fragmentOne = FirstFragment()
    private var fragmentTwo = SecondFragment()
    private var fragmentThree = ThirdFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setFragments()
    }

    private fun setFragments() {
        supportFragmentManager.beginTransaction()
            .add(binding.first.id, fragmentOne)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(binding.second.id, fragmentTwo)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(binding.third.id, fragmentThree)
            .commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        when (fragment) {
            is FirstFragment -> fragment.setFragmentListener(this)
            is SecondFragment -> fragment.setFragmentListener(this)
            is ThirdFragment -> fragment.setFragmentListener(this)
        }
    }

    override fun showUpperCollapsedState(fragmentType: String) {
        when (fragmentType) {

            AppConstants.FIRST_FRAGMENT -> {
                fragmentOne.showUpperCollapsedState()
            }
            AppConstants.SECOND_FRAGMENT -> {
                fragmentTwo.showUpperCollapsedState()
            }
            AppConstants.THIRD_FRAGMENT -> {
                fragmentThree.showUpperCollapsedState()
            }
        }
    }

    override fun showBottomCollapsedState(fragmentType: String) {
        when (fragmentType) {

            AppConstants.FIRST_FRAGMENT -> {
                fragmentOne.showBottomCollapsedState()
            }
            AppConstants.SECOND_FRAGMENT -> {
                fragmentTwo.showBottomCollapsedState()
            }
            AppConstants.THIRD_FRAGMENT -> {
                fragmentThree.showBottomCollapsedState()
            }
        }
    }

    override fun showExpandedState(fragmentType: String) {
        when (fragmentType) {

            AppConstants.FIRST_FRAGMENT -> {
                fragmentOne.showExpandedState()
            }
            AppConstants.SECOND_FRAGMENT -> {
                fragmentTwo.showExpandedState()
            }
            AppConstants.THIRD_FRAGMENT -> {
                fragmentThree.showExpandedState()
            }
        }
    }

    override fun hideBottomCollapsedState(fragmentType: String) {
        fragmentThree.hideCollapsedState()
    }

    override fun onBackPressed() {
        when {
            fragmentThree.isExpanded -> {
                fragmentThree.showBottomCollapsedState()
                fragmentTwo.showExpandedState()
            }
            fragmentTwo.isExpanded -> {
                fragmentThree.hideCollapsedState()
                fragmentTwo.showBottomCollapsedState()
                fragmentOne.showExpandedState()
            }
            fragmentOne.isExpanded -> {
                super.onBackPressed()
            }
            else -> super.onBackPressed()

        }
    }
}