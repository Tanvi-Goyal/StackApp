package com.stackapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stackapp.R
import com.stackapp.constants.AppConstants
import com.stackapp.databinding.FragmentFirstBinding
import com.stackapp.view.interfaces.FragmentListener
import me.tankery.lib.circularseekbar.CircularSeekBar
import kotlin.math.roundToInt

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

        setTexts()
        setExpandedStateListener()
    }

    private fun setTexts() {
        binding.txtDescription.text = resources.getString(R.string.move_the_dial_instruction)
            .replace("*rs*", resources.getString(R.string.Rs))

        binding.valueCredit.text = resources.getString(R.string.Rs) + resources.getString(R.string.credit_amount)
        setSeekbarListener()
    }

    private fun setSeekbarListener() {
        binding.seekbar.setOnSeekBarChangeListener(object : CircularSeekBar.OnCircularSeekBarChangeListener{
            override fun onProgressChanged(
                circularSeekBar: CircularSeekBar?,
                progress: Float,
                fromUser: Boolean
            ) {
                binding.valueCredit.text = resources.getString(R.string.Rs) + progress.roundToInt().toString()
            }

            override fun onStopTrackingTouch(seekBar: CircularSeekBar?) {
            }

            override fun onStartTrackingTouch(seekBar: CircularSeekBar?) {
            }

        })
    }

    private fun setExpandedStateListener() {
        binding.stateExpanded.setOnClickListener {
            showExpandedState()
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