package com.stackapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.NonNull
import com.stackapp.databinding.StackItemBinding

class StackAdapter internal constructor(
    context: Context,
    private val arrayList: ArrayList<String>
) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount() = 3

    override fun getItem(position: Int) = arrayList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        var binding = StackItemBinding.inflate(inflater, parent, false)
        holder = ViewHolder(binding)
        convertView?.tag = holder

        return binding.root
    }

    inner class ViewHolder(private var binding: StackItemBinding) {
    }

}