package com.stackapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stackapp.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var fragmentListener: FragmentListener
    var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.stateExpanded.setOnClickListener {
//            binding.stateExpanded.visibility = View.GONE
//            binding.stateCollapsed.visibility = View.VISIBLE

            fragmentListener.hideBottomCollapsedState(AppConstants.THIRD_FRAGMENT)
            fragmentListener.showBottomCollapsedState(AppConstants.SECOND_FRAGMENT)

        }
    }

    fun showUpperCollapsedState() {
        isExpanded = false
        binding.imgExpand.visibility = View.VISIBLE
        binding.card.visibility = View.INVISIBLE
    }

    fun showBottomCollapsedState() {

    }

    fun showExpandedState() {
        isExpanded = true
        binding.card.visibility = View.VISIBLE
        binding.imgExpand.visibility = View.GONE
    }

    fun setFragmentListener(callback: FragmentListener) {
        this.fragmentListener = callback
    }
}