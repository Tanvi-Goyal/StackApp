package com.stackapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.stackapp.databinding.ItemRepayPlanBinding


class RepayPlanAdapter :
    RecyclerView.Adapter<RepayPlanAdapter.TestsViewHolder>() {

    private var list = ArrayList<RepayModel>()
    var onItemClick: ((RepayModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestsViewHolder {
        val binding: ItemRepayPlanBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_repay_plan, parent, false
        )
        return TestsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (list.size > 0) list.size else 0
    }

    override fun onBindViewHolder(holder: TestsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setData(list: ArrayList<RepayModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class TestsViewHolder(var binding: ItemRepayPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepayModel) {
            binding.executePendingBindings()
            binding.card.setCardBackgroundColor(item.backgroubd)
            binding.planAmount.text = itemView.resources.getString(R.string.Rs) + item.amount + " /mo"
            binding.planDuration.text = "for " + item.duration + " months"
        }
    }
}