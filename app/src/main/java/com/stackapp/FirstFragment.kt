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
        binding.groupUpperExpanded.visibility = View.GONE
        binding.groupUpperCollapsed.visibility = View.VISIBLE
        binding.card.visibility = View.GONE
    }

    fun showBottomCollapsedState() {

    }

    fun showExpandedState() {
        isExpanded = true
        binding.groupUpperExpanded.visibility = View.VISIBLE
        binding.groupUpperCollapsed.visibility = View.GONE
        binding.card.visibility = View.VISIBLE
    }

    fun setFragmentListener(callback: FragmentListener) {
        this.fragmentListener = callback
    }
}