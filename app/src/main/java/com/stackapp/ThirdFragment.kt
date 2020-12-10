package com.stackapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stackapp.databinding.FragmentSecondBinding
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
//            binding.stateCollapsed.visibility = View.GONE
//            binding.stateExpanded.visibility = View.VISIBLE

            showExpandedState()
            fragmentListener.showUpperCollapsedState(AppConstants.SECOND_FRAGMENT)
//            fragmentListener.showBottomCollapsedState(AppConstants.THIRD_FRAGMENT)
        }

        binding.stateExpanded.setOnClickListener {
//            binding.stateExpanded.visibility = View.GONE
//            binding.stateCollapsed.visibility = View.VISIBLE

            showBottomCollapsedState()
            fragmentListener.showExpandedState(AppConstants.SECOND_FRAGMENT)
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