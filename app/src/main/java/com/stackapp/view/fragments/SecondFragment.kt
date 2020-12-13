package com.stackapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stackapp.R
import com.stackapp.constants.AppConstants
import com.stackapp.databinding.FragmentSecondBinding
import com.stackapp.model.RepayModel
import com.stackapp.view.adapter.RepayPlanAdapter
import com.stackapp.view.interfaces.FragmentListener
import com.stackapp.view.interfaces.MyAnimatorListener
import java.util.*


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var fragmentListener: FragmentListener
    private lateinit var adapter: RepayPlanAdapter
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

        setRecycler()
        binding.stateCollapsed.setOnClickListener {
            showExpandedState()
            fragmentListener.showUpperCollapsedState(AppConstants.FIRST_FRAGMENT)
            fragmentListener.showBottomCollapsedState(AppConstants.THIRD_FRAGMENT)

            val slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
            binding.stateExpanded.startAnimation(slideUp)
        }

        binding.groupUpperCollapsed.setOnClickListener {
            showBottomCollapsedState()
            fragmentListener.hideBottomCollapsedState(AppConstants.THIRD_FRAGMENT)
            fragmentListener.showExpandedState(AppConstants.FIRST_FRAGMENT)
        }
    }

    fun showUpperCollapsedState() {
        isExpanded = false
        binding.groupUpperCollapsed.visibility = View.VISIBLE
        binding.groupUpperExpanded.visibility = View.GONE
    }

    fun showBottomCollapsedState() {
        isExpanded = false
        val slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
        binding.stateExpanded.startAnimation(slideDown)
        slideDown.setAnimationListener(object : MyAnimatorListener() {
            override fun onAnimationEnd(p0: Animation?) {
                binding.stateExpanded.visibility = View.GONE
                binding.stateCollapsed.visibility = View.VISIBLE
            }
        })
    }

    fun showExpandedState() {
        isExpanded = true
        binding.stateCollapsed.visibility = View.GONE
        binding.stateExpanded.visibility = View.VISIBLE
        binding.groupUpperCollapsed.visibility = View.GONE
        binding.groupUpperExpanded.visibility = View.VISIBLE
    }

    private fun setRecycler() {
        adapter = RepayPlanAdapter()
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter.setData(createData())

        adapter.onItemClick = { model, position ->
            adapter.updateSelectedPosition(position)
        }
    }

    private fun createData(): ArrayList<RepayModel> {
        return arrayListOf(
            RepayModel(resources.getColor(R.color.color1), "4,247", "12"),
            RepayModel(resources.getColor(R.color.color2), "5,580", "9"),
            RepayModel(resources.getColor(R.color.color3), "8,260", "6")
        )
    }

    fun setFragmentListener(callback: FragmentListener) {
        this.fragmentListener = callback
    }
}