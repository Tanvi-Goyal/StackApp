package com.stackapp

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stackapp.databinding.FragmentSecondBinding
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
        }

        binding.stateExpanded.setOnClickListener {
            fragmentListener.hideBottomCollapsedState(AppConstants.THIRD_FRAGMENT)
            fragmentListener.showExpandedState(AppConstants.FIRST_FRAGMENT)
            showBottomCollapsedState()
        }
    }

    fun showUpperCollapsedState() {
        isExpanded = false
        binding.groupUpperCollapsed.visibility = View.VISIBLE
        binding.groupUpperExpanded.visibility = View.GONE
    }

    fun showBottomCollapsedState() {
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
        binding.groupUpperCollapsed.visibility = View.GONE
        binding.groupUpperExpanded.visibility = View.VISIBLE
    }

    private fun setRecycler() {
        adapter = RepayPlanAdapter()
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter.setData(createData())
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