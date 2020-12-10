package com.stackapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.stackapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StackAdapter

    private var fragmentOne = FirstFragment()
    private var fragmentTwo = SecondFragment()
    private var fragmentThree = ThirdFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        supportFragmentManager.beginTransaction()
            .add(binding.first.id, fragmentOne)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(binding.second.id, fragmentTwo)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(binding.third.id, fragmentThree)
            .addToBackStack("third").commit()
        /*
        Tasks
        1. ist fragment in expanded view
        2. on click of 2nd collapsed view ->
            slide up and expand with animation
            collapse 1st view on animation end of 2nd view
            show 3rd collapsed view
        3. on click of 3rd collapsed view
            slide up and expand wt=ith animation
            collapse 2nd view on animation end of 3rd view
            show 4th collapsed view
        4. on Click 3rd expanded view
            3rd view has to collapse
            2nd view to show loading view till 3rd view animation end
            2nd view to show expanded state on animation end
        5. All view are expanded and click on 2nd view
            animate 3rd view and collapse
         */
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
        }

//        super.onBackPressed()

    }
}