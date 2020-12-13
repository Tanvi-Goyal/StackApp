package com.stackapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.stackapp.R
import com.stackapp.constants.AppConstants
import com.stackapp.databinding.FragmentThirdBinding
import com.stackapp.view.interfaces.FragmentListener
import com.stackapp.view.interfaces.MyAnimatorListener

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

        setCollapsedStateListener()
        setExpandedStateListener()
    }

    private fun setCollapsedStateListener() {
        binding.stateCollapsed.setOnClickListener {
            showExpandedState()
            fragmentListener.showUpperCollapsedState(AppConstants.SECOND_FRAGMENT)

            val slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
            binding.stateExpanded.startAnimation(slideUp)
        }
    }

    private fun setExpandedStateListener() {
        binding.stateExpanded.setOnClickListener {
            showBottomCollapsedState()
            fragmentListener.showExpandedState(AppConstants.SECOND_FRAGMENT)
        }
    }

    fun showUpperCollapsedState() {
    }

    fun showBottomCollapsedState() {
        if (isExpanded) {
            isExpanded = false
            val slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
            binding.stateExpanded.startAnimation(slideDown)
            slideDown.setAnimationListener(object : MyAnimatorListener() {
                override fun onAnimationEnd(p0: Animation?) {
                    binding.stateExpanded.visibility = View.GONE
                    binding.stateCollapsed.visibility = View.VISIBLE
                }
            })
        } else {
            isExpanded = false
            binding.stateExpanded.visibility = View.GONE
            binding.stateCollapsed.visibility = View.VISIBLE
        }
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