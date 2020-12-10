package com.stackapp

interface FragmentListener {

    fun showUpperCollapsedState(fragmentType: String)
    fun showBottomCollapsedState(fragmentType: String)
    fun showExpandedState(fragmentType: String)
    fun hideBottomCollapsedState(fragmentType: String)
}