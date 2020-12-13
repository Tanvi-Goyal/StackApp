package com.stackapp.view.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stackapp.view.interfaces.FragmentListener
import com.stackapp.constants.AppConstants
import com.stackapp.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private lateinit var fragmentListener: FragmentListener
    var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.stateCollapsed.setOnClickListener {
            showExpandedState()
            fragmentListener.showUpperCollapsedState(AppConstants.SECOND_FRAGMENT)
//            fragmentListener.showBottomCollapsedState(AppConstants.THIRD_FRAGMENT)
        }

        binding.stateExpanded.setOnClickListener {
            fragmentListener.showExpandedState(AppConstants.SECOND_FRAGMENT)
            showBottomCollapsedWithDelay()
//            fragmentListener.hideBottomCollapsedState(AppConstants.THIRD_FRAGMENT)
        }
    }

    fun showUpperCollapsedState() {

    }

    fun showBottomCollapsedState() {
        isExpanded = false
        binding.stateExpanded.visibility = View.GONE
        binding.stateCollapsed.visibility = View.VISIBLE
    }

    fun showBottomCollapsedWithDelay() {
        isExpanded = false
        binding.stateExpanded.visibility = View.GONE
        Handler().postDelayed({
            binding.stateCollapsed.visibility = View.VISIBLE
        }, 200)
    }

    fun showExpandedState() {
        isExpanded = true
        binding.stateCollapsed.visibility = View.GONE
        binding.stateExpanded.visibility = View.VISIBLE
    }

    fun hideCollapsedState() {
        isExpanded = false
        binding.stateCollapsed.visibility = View.GONE
        binding.stateExpanded.visibility = View.GONE
    }

    fun setFragmentListener(callback: FragmentListener) {
        this.fragmentListener = callback
    }

}