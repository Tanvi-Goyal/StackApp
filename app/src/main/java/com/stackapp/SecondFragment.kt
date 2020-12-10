package com.stackapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stackapp.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var fragmentListener: FragmentListener
    var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.stateCollapsed.setOnClickListener {
//            binding.stateCollapsed.visibility = View.GONE
//            binding.stateExpanded.visibility = View.VISIBLE

            showExpandedState()
            fragmentListener.showUpperCollapsedState(AppConstants.FIRST_FRAGMENT)
            fragmentListener.showBottomCollapsedState(AppConstants.THIRD_FRAGMENT)
        }

        binding.stateExpanded.setOnClickListener {
//            binding.stateExpanded.visibility = View.GONE
//            binding.stateCollapsed.visibility = View.VISIBLE

            showBottomCollapsedState()
            fragmentListener.showExpandedState(AppConstants.FIRST_FRAGMENT)
            fragmentListener.hideBottomCollapsedState(AppConstants.THIRD_FRAGMENT)
        }
    }

    fun showUpperCollapsedState() {
        isExpanded = false
        binding.imgExpand.visibility = View.VISIBLE
        binding.card.visibility = View.INVISIBLE
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
        binding.imgExpand.visibility = View.GONE
        binding.card.visibility = View.VISIBLE
    }

    fun setFragmentListener(callback: FragmentListener) {
        this.fragmentListener = callback
    }
}